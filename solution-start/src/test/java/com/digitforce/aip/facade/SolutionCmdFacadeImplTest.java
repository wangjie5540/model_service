package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class SolutionCmdFacadeImplTest extends BaseTest {
    @Resource
    private SolutionCmdFacade solutionCmdFacade;

    @Test
    public void delete() {
        SolutionDeleteCmd solutionDeleteCmd = new SolutionDeleteCmd();
        solutionDeleteCmd.setId(240L);
        solutionCmdFacade.delete(solutionDeleteCmd);
    }
}