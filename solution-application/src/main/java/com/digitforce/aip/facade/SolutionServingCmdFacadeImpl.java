package com.digitforce.aip.facade;

import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.service.cmd.SolutionServingCmdService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
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
public class SolutionServingCmdFacadeImpl implements SolutionServingCmdFacade {
    @Resource
    private SolutionServingCmdService solutionServingCmdService;

    @Override
    public Result add(SolutionServingAddCmd solutionServingAddCmd) {
        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
        solutionServingCmdService.save(solutionServing);
        return Result.success(solutionServing.getId());
    }
}
