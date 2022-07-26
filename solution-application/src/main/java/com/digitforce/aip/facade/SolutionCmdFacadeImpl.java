package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.*;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方案命令接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:40
 */
@RestController
public class SolutionCmdFacadeImpl implements SolutionCmdFacade {
    @Resource
    private SolutionCmdService solutionCmdService;

    @Override
    public Result add(SolutionAddCmd solutionAddCmd) {
        solutionCmdService.add(solutionAddCmd);
        return Result.success();
    }

    @Override
    public Result on(SolutionStatusCmd solutionStatusCmd) {
        solutionCmdService.on(solutionStatusCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchOn(SolutionBatchStatusCmd solutionBatchStatusCmd) {
        solutionCmdService.batchOn(solutionBatchStatusCmd.getIds());
        return Result.success();
    }

    @Override
    public Result off(SolutionStatusCmd solutionStatusCmd) {
        solutionCmdService.off(solutionStatusCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchOff(SolutionBatchStatusCmd solutionBatchStatusCmd) {
        solutionCmdService.batchOff(solutionBatchStatusCmd.getIds());
        return Result.success();
    }

    @Override
    public Result addCron(SolutionAddCronCmd solutionAddCronCmd) {
        return null;
    }

    @Override
    public Result clearCron(SolutionClearCronCmd solutionClearCronCmd) {
        return null;
    }

    @Override
    public Result execute(SolutionStatusCmd solutionStatusCmd) {
        solutionCmdService.execute(solutionStatusCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchExecute(SolutionBatchStatusCmd solutionBatchStatusCmd) {
        solutionCmdService.batchExecute(solutionBatchStatusCmd.getIds());
        return Result.success();
    }

    @Override
    public Result stop(SolutionStatusCmd solutionStatusCmd) {
        solutionCmdService.stop(solutionStatusCmd.getId());
        return Result.success();
    }

    @Override
    public Result delete(SolutionDeleteCmd solutionDeleteCmd) {
        solutionCmdService.delete(solutionDeleteCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchDelete(SolutionBatchStatusCmd solutionBatchStatusCmd) {
        solutionCmdService.batchDelete(solutionBatchStatusCmd.getIds());
        return Result.success();
    }
}
