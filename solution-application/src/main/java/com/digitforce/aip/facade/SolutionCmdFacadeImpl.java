package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * 方案服务命令接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:40
 */
@RestController
public class SolutionCmdFacadeImpl implements SolutionCmdFacade {
    @Override
    public Result add(SolutionAddCmd solutionAddCmd) {
        return null;
    }

    @Override
    public Result on(SolutionAddCmd solutionAddCmd) {
        return null;
    }

    @Override
    public Result off(SolutionAddCmd solutionAddCmd) {
        return null;
    }

    @Override
    public Result delete(SolutionAddCmd solutionAddCmd) {
        return null;
    }

    @Override
    public Result modify(SolutionAddCmd solutionAddCmd) {
        return null;
    }
}
