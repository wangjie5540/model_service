package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAndRun(SolutionServing solutionServing) {
        Solution solution = solutionService.getById(solutionServing.getSolutionId());
        if (Objects.isNull(solution)) {
            log.error("方案不存在");
            return;
        }
        SolutionRun solutionRun = solutionRunMapper.getLatestRunBySolutionId(solution.getId());
        if (Objects.isNull(solutionRun)) {
            log.error("方案执行实例不存在");
            return;
        }
        ServingInstance servingInstance = ConvertTool.convert(solutionServing, ServingInstance.class);
        servingInstance.setId(null);
        servingInstance.setServingId(solutionServing.getId());
        servingInstance.setStatus(ServingInstanceStatusEnum.PREDICTING);
        super.save(servingInstance);
        Long servingInstanceId = servingInstance.getId();
        Map<String, Object> templateParams = solutionServing.getTemplateParams();
        // TODO 使用servingInstanceId目前是不能索引到训练产生的模型的，需要修改
//        templateParams.put("serving_instance_id", servingInstanceId.toString());
        templateParams.put("serving_instance_id", solutionRun.getId().toString());
        String pipelineParams = templateComponent.getPipelineParams(solutionServing.getPipelineTemplate(),
            templateParams);
        String pRunName = String.format("%s-%s", solution.getPipelineName(), servingInstanceId);
        String pRunId = kubeflowPipelineService.createRun(solution.getPipelineId(), pRunName, pipelineParams,
            PipelineRunFlagEnum.PREDICT.name());
        servingInstance = new ServingInstance();
        servingInstance.setId(servingInstanceId);
        servingInstance.setPRunId(pRunId);
        servingInstance.setPRunName(pRunName);
        super.updateById(servingInstance);
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
