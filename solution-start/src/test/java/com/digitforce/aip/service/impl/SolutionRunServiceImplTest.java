package com.digitforce.aip.service.impl;

import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.aip.service.ISolutionRunService;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

public class SolutionRunServiceImplTest extends BaseTest {
    @Resource
    private ISolutionRunService solutionRunService;

    @Test
    public void createRun() throws InterruptedException {
        solutionRunService.createRun(1L, "3d49c45b-21bb-423f-bf62-59f31c496724", "test", SolutionRunTypeEnum.DEBUG);
        TimeUnit.SECONDS.sleep(1000);
    }
}