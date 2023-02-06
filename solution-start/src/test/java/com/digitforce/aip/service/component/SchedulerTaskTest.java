package com.digitforce.aip.service.component;

import com.digitforce.aip.entity.SolutionRun;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;


public class SchedulerTaskTest extends BaseTest {
    @Resource
    private SchedulerTask schedulerTask;

    @Test
    public void parseModelPackage() {
        SolutionRun solutionRun = new SolutionRun();
        solutionRun.setSolutionId(39L);
        solutionRun.setPRunName("loss_warning_v10_dev-1612027828000432130");
        solutionRun.setId(1612027828000432130L);
        schedulerTask.parseModelPackage(solutionRun);
    }
}