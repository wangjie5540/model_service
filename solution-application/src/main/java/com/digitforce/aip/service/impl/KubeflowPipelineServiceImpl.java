package com.digitforce.aip.service.impl;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.dto.data.Pipeline;
import com.digitforce.aip.entity.PipelinePage;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.utils.KubeflowHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class KubeflowPipelineServiceImpl implements KubeflowPipelineService {
    @Resource
    private KubeflowProperties kubeflowProperties;

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
    public String createRun(String pipelineId, String runName, String pipelineParams, String flag) {
        return KubeflowHelper.createRun(
                kubeflowProperties.getHost(),
                kubeflowProperties.getPort(),
                kubeflowProperties.getExperimentId(),
                pipelineId,
                runName,
                pipelineParams,
                flag
        );
    }

    @Override
    public RunStatusEnum getStatus(String runId) {
        String status = KubeflowHelper.getStatus(kubeflowProperties.getHost(), kubeflowProperties.getPort(), runId);
        return RunStatusEnum.valueOf(status);
    }

    @Override
    public void stopRun(String runId) {
        KubeflowHelper.stopRun(kubeflowProperties.getHost(), kubeflowProperties.getPort(), runId);
    }
}
