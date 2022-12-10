package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionBatchStatusCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.framework.api.dto.Result;
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(rollbackFor = RuntimeException.class)
    public Result add(SolutionAddCmd solutionAddCmd) {
        solutionCmdService.add(solutionAddCmd);
        return Result.success();
    }

    @Override
    public Result publish(SolutionPublishCmd solutionPublishCmd) {
        solutionCmdService.on(solutionPublishCmd.getId());
        return Result.success();
    }

    @Override
    public Result off(SolutionPublishCmd solutionPublishCmd) {
        solutionCmdService.off(solutionPublishCmd.getId());
        return Result.success();
    }

    @Override
    public Result execute(SolutionPublishCmd solutionStatusCmd) {
        solutionCmdService.execute(solutionStatusCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchExecute(SolutionBatchStatusCmd solutionBatchStatusCmd) {
        solutionCmdService.batchExecute(solutionBatchStatusCmd.getIds());
        return Result.success();
    }

    @Override
    public Result stop(SolutionPublishCmd solutionStatusCmd) {
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

    @Override
    public Result modifyById(SolutionModifyCmd solutionModifyCmd) {
        solutionCmdService.modifyById(solutionModifyCmd);
        return Result.success();
    }
}
