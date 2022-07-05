package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateOnlineCmd;
import com.digitforce.aip.service.cmd.SolutionTemplateCmdService;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 方案服务命令接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:40
 */
@RestController
public class SolutionTemplateCmdFacadeImpl implements SolutionTemplateCmdFacade {
    @Resource
    private SolutionTemplateCmdService solutionCmdService;

    @Override
    public Result add(SolutionTemplateAddCmd solutionAddCmd) {
        solutionCmdService.add(solutionAddCmd);
        return Result.success();
    }

    @Override
    public Result on(SolutionTemplateOnlineCmd solutionOnlineCmd) {
        solutionCmdService.on(solutionOnlineCmd.getId());
        return Result.success();
    }

    @Override
    public Result off(SolutionTemplateOnlineCmd solutionOnlineCmd) {
        solutionCmdService.off(solutionOnlineCmd.getId());
        return Result.success();
    }

    @Override
    public Result delete(SolutionTemplateDeleteCmd solutionDeleteCmd) {
        solutionCmdService.delete(solutionDeleteCmd.getId());
        return Result.success();
    }

    @Override
    public Result modify(SolutionTemplateModifyCmd solutionModifyCmd) {
        solutionCmdService.modify(solutionModifyCmd);
        return Result.success();
    }
}
