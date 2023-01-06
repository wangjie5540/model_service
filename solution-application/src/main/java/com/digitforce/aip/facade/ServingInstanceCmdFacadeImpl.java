package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.ServingInstanceAddCmd;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.service.IServingInstanceService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.framework.api.dto.Result;

import javax.annotation.Resource;

public class ServingInstanceCmdFacadeImpl implements ServingInstanceCmdFacade {
    @Resource
    private ISolutionServingService solutionServingService;
    @Resource
    private IServingInstanceService servingInstanceService;

    @Override
    public Result add(ServingInstanceAddCmd servingInstanceAddCmd) {
        SolutionServing solutionServing = solutionServingService.getById(servingInstanceAddCmd.getServingId());
        return null;
    }
}
