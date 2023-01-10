package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.PipelineRunFlagEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.service.component.TemplateComponent;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 方案执行表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-10
 */
@Service
public class SolutionRunServiceImpl extends ServiceImpl<SolutionRunMapper, SolutionRun> implements ISolutionRunService {
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private TemplateComponent templateComponent;

    @Override
    @SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public void createRun(Solution solution, SolutionRunTypeEnum type, Map<String, Object> templateParams) {
        SolutionRun solutionRun = ConvertTool.convert(solution, SolutionRun.class);
        solutionRun.setSolutionId(solution.getId());
        solutionRun.setId(null);
        solutionRun.setType(type);
        super.save(solutionRun);
        Long solutionRunId = solutionRun.getId();
        templateParams.put("solution_run_id", solutionRunId.toString());
        String pipelineParams = templateComponent.getPipelineParams(solution.getTrainTemplate(), templateParams);
        String pRunName = StrUtil.format("{}-{}", solution.getPipelineName(), solutionRunId);
        String pRunId = kubeflowPipelineService.createRun(solution.getPipelineId(), pRunName, pipelineParams,
                PipelineRunFlagEnum.TRAIN.name());
        solutionRun = new SolutionRun();
        solutionRun.setId(solutionRunId);
        solutionRun.setPRunId(pRunId);
        solutionRun.setPRunName(pRunName);
        solutionRun.setPipelineParams(pipelineParams);
        super.updateById(solutionRun);
    }

    @Override
    public PageView<SolutionRun> page(SolutionRunPageByQry solutionRunPageByQry) {
        QueryWrapper<SolutionRun> queryWrapper =
                new QueryWrapper<>(BeanUtil.toBean(solutionRunPageByQry.getClause(), SolutionRun.class));
        Map<String, Object> map = BeanUtil.beanToMap(solutionRunPageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<SolutionRun> page = PageUtil.page(solutionRunPageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }
}
