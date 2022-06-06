package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionOnlineCmd;
import com.digitforce.aip.service.cmd.SolutionCmdService;
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
public class SolutionCmdFacadeImpl implements SolutionCmdFacade {
//    private final SolutionStructMapper solutionStructMapper = Mappers.getMapper(SolutionStructMapper.class);

    @Resource
    private SolutionCmdService solutionCmdService;

    @Override
    public Result add(SolutionAddCmd solutionAddCmd) {
        solutionCmdService.add(solutionAddCmd);
        return Result.success();
    }

    @Override
    public Result on(SolutionOnlineCmd solutionOnlineCmd) {
        solutionCmdService.on(solutionOnlineCmd.getId());
        return Result.success();
    }

    @Override
    public Result off(SolutionOnlineCmd solutionOnlineCmd) {
        solutionCmdService.off(solutionOnlineCmd.getId());
        return Result.success();
    }

    @Override
    public Result delete(SolutionDeleteCmd solutionDeleteCmd) {
        solutionCmdService.delete(solutionDeleteCmd.getId());
        return Result.success();
    }

    @Override
    public Result modify(SolutionModifyCmd solutionModifyCmd) {
        solutionCmdService.modify(solutionModifyCmd);
        return Result.success();
    }
}
