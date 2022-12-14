package com.digitforce.aip.service.impl;

import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class SolutionServiceImplTest extends BaseTest {
    @Resource
    private ISolutionService solutionService;

    @Test
    public void createAndRun() throws InterruptedException {
        SolutionAddCmd solutionAddCmd = new SolutionAddCmd();
        solutionAddCmd.setPipelineId("3d49c45b-21bb-423f-bf62-59f31c496724");
        solutionAddCmd.setPipelineName("test");
        solutionAddCmd.setAutoML(false);
        solutionAddCmd.setSceneId(2L);
        solutionAddCmd.setSystem("aaa");
        solutionAddCmd.setTitle("title");
        solutionAddCmd.setSceneName("sceneName");
        solutionService.createAndRun(solutionAddCmd);
        TimeUnit.SECONDS.sleep(1000);
    }
}