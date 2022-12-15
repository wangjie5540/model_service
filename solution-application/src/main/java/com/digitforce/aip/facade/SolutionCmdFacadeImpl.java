package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
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
    private ISolutionService solutionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(SolutionAddCmd solutionAddCmd) {
        solutionService.createAndRun(solutionAddCmd);
        return Result.success();
    }

    @Override
    public Result publish(SolutionPublishCmd solutionPublishCmd) {
        Solution solution = ConvertTool.convert(solutionPublishCmd, Solution.class);
        solutionService.updateById(solution);
        return Result.success();
    }

    @Override
    public Result off(SolutionPublishCmd solutionPublishCmd) {
        return Result.success();
    }

    @Override
    public Result execute(SolutionPublishCmd solutionStatusCmd) {
        return Result.success();
    }

    @Override
    public Result stop(SolutionPublishCmd solutionStatusCmd) {
        return Result.success();
    }

    @Override
    public Result delete(SolutionDeleteCmd solutionDeleteCmd) {
        return Result.success();
    }

    @Override
    public Result modifyById(SolutionModifyCmd solutionModifyCmd) {
        return Result.success();
    }
}
