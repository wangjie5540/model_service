package com.digitforce.aip.service.impl;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.model.PipelinePage;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.KubeflowPipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class KubeflowPipelineServiceImpl implements KubeflowPipelineService {
    @Resource
    private KubeflowProperties kubeflowProperties;
    @Resource
    private ISolutionRunService solutionRunService;

    @Override
    public PipelinePage pageByPipeline(Integer pageSize) {
        return KubeflowHelper.pageByPipeline(kubeflowProperties.getHost(), kubeflowProperties.getPort(), pageSize);
    }

    @Override
    public Pipeline getPipelineById(String pipelineId) {
        return KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(),
            kubeflowProperties.getPort(), pipelineId);
    }

    @Override
    public String createRun(String pipelineId, String runName, String pipelineParams) {
        return KubeflowHelper.createRun(
            kubeflowProperties.getHost(),
            kubeflowProperties.getPort(),
            kubeflowProperties.getExperimentId(),
            pipelineId,
            runName,
            pipelineParams
        );
    }

    @Override
    public RunStatusEnum getStatus(String runId) {
        String status = KubeflowHelper.getStatus(kubeflowProperties.getHost(), kubeflowProperties.getPort(), runId);
        return RunStatusEnum.valueOf(status);
    }
}
