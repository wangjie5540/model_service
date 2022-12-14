package com.digitforce.aip.service.impl;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.aip.model.PipelinePage;
import com.digitforce.aip.service.KubeflowPipelineService;
import com.digitforce.aip.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

public class KubeflowPipelineServiceImplTest extends BaseTest {
    @Resource
    private KubeflowPipelineService kubeflowPipelineService;
    @Resource
    private KubeflowProperties kubeflowProperties;

    @Test
    public void pageByPipeline() {
        PipelinePage pipelinePage = kubeflowPipelineService.pageByPipeline(10);
        Assert.assertNotNull(pipelinePage);
    }

    @Test
    public void getPipelineById() {
        Pipeline pipeline = kubeflowPipelineService.getPipelineById("3d49c45b-21bb-423f-bf62-59f31c496724");
        Assert.assertNotNull(pipeline);
    }

    @Test
    public void createRun() {
        String runId = kubeflowPipelineService.createRun("3d49c45b-21bb-423f-bf62-59f31c496724", "test");
        Assert.assertNotNull(runId);
    }
}