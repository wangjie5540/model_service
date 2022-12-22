package com.digitforce.aip.service.impl;

import com.digitforce.aip.dto.data.PipelineParams;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

public class SolutionRunServiceImplTest extends BaseTest {
    @Resource
    private ISolutionRunService solutionRunService;
    @Resource
    private ISolutionService solutionService;
    @Resource
    private Scheduler scheduler;

    @Test
    public void createRun() throws InterruptedException {
        Solution solution = solutionService.getById(1L);
        solution.setPipelineId("cd9a12b9-8407-42bb-bce3-c5bcf166e2c3");
        solution.setPipelineName("test11");
        PipelineParams pipelineParams = new PipelineParams();
        solutionRunService.createRun(solution, pipelineParams, SolutionRunTypeEnum.DEBUG);
        TimeUnit.SECONDS.sleep(1000);
    }

    @Test
    public void stop() throws SchedulerException {
        scheduler.clear();
    }
}