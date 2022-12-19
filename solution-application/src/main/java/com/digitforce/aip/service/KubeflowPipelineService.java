package com.digitforce.aip.service;

import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.model.PipelinePage;

/**
 * Kubeflow Pipeline Service
 *
 * @author wangtonggui
 */
public interface KubeflowPipelineService {
    PipelinePage pageByPipeline(Integer pageSize);

    Pipeline getPipelineById(String pipelineId);

    String createRun(String pipelineId, String runName);

    RunStatusEnum getStatus(String runId);
}
