package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.transaction.annotation.Transactional;
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
    private ISolutionServingService solutionServingService;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result add(SolutionServingAddCmd solutionServingAddCmd) {
        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
        solutionServing.setCreateUser(TenantContext.tenant().getUserAccount());
        solutionServing.setUpdateUser(TenantContext.tenant().getUserAccount());
        solutionServingService.save(solutionServing);
        return Result.success();
    }
}
