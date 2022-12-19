package com.digitforce.aip.service.impl;

import com.digitforce.aip.KubeflowHelper;
import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.model.PipelinePage;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.KubeflowPipelineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    public String createRun(String pipelineId, String runName) {
        return KubeflowHelper.createRun(
                kubeflowProperties.getHost(),
                kubeflowProperties.getPort(),
                kubeflowProperties.getExperimentId(),
                pipelineId,
                runName,
                "{}"
        );
    }

    @Override
    public RunStatusEnum getStatus(String runId) {
        String status = KubeflowHelper.getStatus(kubeflowProperties.getHost(), kubeflowProperties.getPort(), runId);
        return RunStatusEnum.valueOf(status);
    }

    //    @Override
    public void checkStatusAsync(SolutionRun solutionRun) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            String status = KubeflowHelper.getStatus(kubeflowProperties.getHost(), kubeflowProperties.getPort(),
                    solutionRun.getPRunId());
            log.info("runId: {}, status: {}", solutionRun.getPRunId(), status);
            switch (RunStatusEnum.valueOf(status)) {
                case Succeeded:
//                    solutionRunService.u(runId, RunStatusEnum.Succeeded);
                    break;
                case Failed:
                    System.out.println("Failed");
                    break;
                case Running:
                    log.info("In this status, do nothing but next check");
                    break;
                default:
                    log.warn("pipeline run status is unknown.[runId={}, status={}]", solutionRun.getPRunId(), status);
                    break;
            }
        }, 0, 5, TimeUnit.SECONDS);
    }
}
