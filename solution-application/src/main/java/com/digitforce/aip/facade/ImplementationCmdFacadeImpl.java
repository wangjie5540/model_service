package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.ImplementationAddCmd;
import com.digitforce.aip.dto.cmd.ImplementationTriggerCmd;
import com.digitforce.aip.service.cmd.ImplementationCmdService;
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
public class ImplementationCmdFacadeImpl implements ImplementationCmdFacade {
    @Resource
    private ImplementationCmdService implementationCmdService;

    @Override
    public Result add(ImplementationAddCmd implementAddCmd) {
        implementationCmdService.add(implementAddCmd);
        return Result.success();
    }

    @Override
    public Result triggerRun(ImplementationTriggerCmd implementationTriggerCmd) {
        // TODO 依赖任务服务新增接口定义
        implementationCmdService.triggerRun(implementationTriggerCmd);
        return null;
    }
}
