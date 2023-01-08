package com.digitforce.aip.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.PipelineRunFlagEnum;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.mapper.SolutionRunMapper;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.service.component.TemplateComponent;
import com.digitforce.framework.tool.ConvertTool;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import javax.annotation.Resource;

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
        String pipelineParams = templateComponent.getPipelineParams(solution.getPipelineTemplate(), templateParams);
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
    public RunStatusEnum getRunStatus(String runId) {
        return kubeflowPipelineService.getStatus(runId);
    }
}
