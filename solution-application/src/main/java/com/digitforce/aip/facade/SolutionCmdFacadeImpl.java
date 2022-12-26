package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.dto.cmd.SolutionUnPublishCmd;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.framework.api.dto.Result;
import lombok.SneakyThrows;
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
    @Transactional(rollbackFor = Exception.class)
    public Result publish(SolutionPublishCmd solutionPublishCmd) {
        solutionService.publish(solutionPublishCmd);
        return Result.success();
    }

    @SneakyThrows
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result unPublish(SolutionUnPublishCmd solutionUnPublishCmd) {
        solutionService.unPublish(solutionUnPublishCmd);
        return Result.success();
    }

    @Override
    public Result delete(SolutionDeleteCmd solutionDeleteCmd) {
        Solution solution = solutionService.getById(solutionDeleteCmd.getId());
        if (solution == null) {
            throw new RuntimeException("方案不存在");
        }
        if (solution.getStatus() == SolutionStatusEnum.EXECUTING
                || solution.getStatus() == SolutionStatusEnum.PUBLISHED) {
            throw new RuntimeException("方案正在执行或已发布，不能删除");
        }
        solutionService.removeById(solutionDeleteCmd.getId());
        return Result.success();
    }

    @Override
    public Result modifyById(SolutionModifyCmd solutionModifyCmd) {
        return Result.success();
    }
}
