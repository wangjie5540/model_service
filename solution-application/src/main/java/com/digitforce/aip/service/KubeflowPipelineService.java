package com.digitforce.aip.service;

import com.digitforce.aip.dto.data.Pipeline;
import com.digitforce.aip.entity.PipelinePage;
import com.digitforce.aip.enums.RunStatusEnum;

/**
 * Kubeflow Pipeline Service
 *
 * @author wangtonggui
 */
public interface KubeflowPipelineService {
    PipelinePage pageByPipeline(Integer pageSize);

    Pipeline getPipelineById(String pipelineId);

    String createRun(String pipelineId, String runName, String pipelineParams, String flag);

    RunStatusEnum getStatus(String runId);

    void stopRun(String runId);
}
