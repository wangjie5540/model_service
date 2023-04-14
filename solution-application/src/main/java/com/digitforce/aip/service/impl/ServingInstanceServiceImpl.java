package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.consts.SolutionErrorCode;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
import com.digitforce.aip.entity.ServingInstance;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.enums.PipelineRunFlagEnum;
import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import com.digitforce.aip.mapper.ServingInstanceMapper;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.service.component.TemplateComponent;
import com.digitforce.aip.utils.ApplicationUtil;
import com.digitforce.aip.utils.OlapHelper;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.exception.BizException;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.domain.Tenant;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 服务实例表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
@Service
@Slf4j
public class ServingInstanceServiceImpl extends ServiceImpl<ServingInstanceMapper, ServingInstance>
        implements IServingInstanceService {
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private ISolutionService solutionService;
    @Resource
    private TemplateComponent templateComponent;
    @Resource
    private SolutionRunMapper solutionRunMapper;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @SneakyThrows
    public ServingInstance createAndRun(SolutionServing solutionServing) {
        Solution solution = solutionService.getById(solutionServing.getSolutionId());
        if (Objects.isNull(solution)) {
            throw BizException.of(SolutionErrorCode.SOLUTION_NOT_FOUND);
        }
        SolutionRun solutionRun = solutionRunMapper.getLatestRunBySolutionId(solution.getId());
        if (Objects.isNull(solutionRun)) {
            throw BizException.of(SolutionErrorCode.SOLUTION_RUN_NOT_FOUND);
        }
        ServingInstance servingInstance = ConvertTool.convert(solutionServing, ServingInstance.class);
        servingInstance.setId(null);
        servingInstance.setServingId(solutionServing.getId());
        servingInstance.setStatus(ServingInstanceStatusEnum.PREDICTING);
        super.save(servingInstance);
        Long servingInstanceId = servingInstance.getId();
        Map<String, Object> templateParams = solutionServing.getTemplateParams() == null ? Maps.newHashMap() :
                solutionServing.getTemplateParams();
        // 填充预测模板参数
        templateParams.put("result_file_name",
                ApplicationUtil.generateServingResultFileName(TenantContext.tenantId(), servingInstance.getId()));
        String encode = Tenant.encode(TenantContext.tenant());
        templateParams.putAll(solution.getTemplateParams());
        String pipelineParams = templateComponent.getPipelineParams(solutionServing.getPipelineTemplate(),
                templateParams);
        Map<String, Object> map = objectMapper.readValue(pipelineParams, new TypeReference<Map<String, Object>>() {
        });
        map.put("X_TENANT", encode);
        // 添加starrocks表名
        map.put("predict_table_name", OlapHelper.getScoreTableName(solution.getId()));
        map.put("shapley_table_name", OlapHelper.getShapleyTableName(solution.getId()));
        // 添加表分区
        map.put("instance_id", servingInstance.getId().toString());
        pipelineParams = objectMapper.writeValueAsString(map);
        String pRunName = String.format("%s-%s", solution.getPipelineName(), servingInstanceId);
        String pRunId = kubeflowPipelineService.createRun(solution.getPipelineId(), pRunName, pipelineParams,
                PipelineRunFlagEnum.PREDICT.name());
        ServingInstance updateServingInstance = new ServingInstance();
        updateServingInstance.setId(servingInstanceId);
        updateServingInstance.setPRunId(pRunId);
        updateServingInstance.setPRunName(pRunName);
        super.updateById(updateServingInstance);
        return servingInstance;
    }

    @Override
    public PageView<ServingInstance> page(ServingInstancePageByQry servingInstancePageByQry) {
        QueryWrapper<ServingInstance> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(servingInstancePageByQry.getClause(), ServingInstance.class));
        Map<String, Object> map = BeanUtil.beanToMap(servingInstancePageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<ServingInstance> page = PageUtil.page(servingInstancePageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
