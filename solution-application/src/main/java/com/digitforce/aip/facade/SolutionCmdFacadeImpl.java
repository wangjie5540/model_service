package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.*;
import com.digitforce.aip.service.cmd.SolutionCmdService;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 策略命令接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:40
 */
@RestController
public class SolutionCmdFacadeImpl implements SolutionCmdFacade {
    @Resource
    private SolutionCmdService implementationCmdService;

    @Override
    public Result add(SolutionAddCmd implementAddCmd) {
        implementationCmdService.add(implementAddCmd);
        return Result.success();
    }

    @Override
    public Result on(SolutionAddCmd implementAddCmd) {
        return null;
    }

    @Override
    public Result off(SolutionOnOffCmd solutionOnOffCmd) {
        return null;
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
    public Result triggerRun(SolutionTriggerCmd implementationTriggerCmd) {
        // TODO 依赖任务服务新增接口定义
        implementationCmdService.triggerRun(implementationTriggerCmd);
        return null;
    }
}
