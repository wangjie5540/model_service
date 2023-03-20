package com.digitforce.aip.facade;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.digitforce.aip.consts.SolutionErrorCode;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionControlCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.dto.cmd.SolutionUnPublishCmd;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.mapper.SceneMapper;
import com.digitforce.aip.mapper.StarrocksDDLMapper;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.aip.utils.OlapHelper;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.api.exception.BizException;
import com.digitforce.framework.tool.ConvertTool;
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
    @Resource
    private ISolutionServingService solutionServingService;
    @Resource
    private SceneMapper sceneMapper;
    @Resource
    private StarrocksDDLMapper starrocksDDLMapper;

    @Override
    public Result add(SolutionAddCmd solutionAddCmd) {
        Solution solution = solutionService.add(solutionAddCmd);
        // 创建starrocks表
        starrocksDDLMapper.createUserScoreTable(OlapHelper.getScoreTableName(solution.getId()));
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
    @Transactional(rollbackFor = Exception.class)
    public Result delete(SolutionDeleteCmd solutionDeleteCmd) {
        Solution solution = solutionService.getById(solutionDeleteCmd.getId());
        if (solution == null) {
            throw BizException.of(SolutionErrorCode.SOLUTION_NOT_FOUND);
        } else if (solution.getStatus() == SolutionStatusEnum.EXECUTING) {
            throw BizException.of(SolutionErrorCode.SOLUTION_EXECUTING);
        } else if (solution.getStatus() == SolutionStatusEnum.PUBLISHED) {
            throw BizException.of(SolutionErrorCode.SOLUTION_PUBLISHED);
        }
        long count = solutionServingService.count(
                new LambdaQueryWrapper<SolutionServing>().eq(SolutionServing::getSolutionId, solution.getId()));
        if (count > 0) {
            throw BizException.of(SolutionErrorCode.SOLUTION_HAS_SERVING);
        }
        solutionService.removeById(solutionDeleteCmd.getId());
        sceneMapper.decreaseSolutionCount(solution.getSceneId());
        return Result.success();
    }

    @Override
    public Result modifyById(SolutionModifyCmd solutionModifyCmd) {
        Solution solution = ConvertTool.convert(solutionModifyCmd, Solution.class);
        solutionService.updateById(solution);
        return Result.success();
    }

    @Override
    public Result stopRun(SolutionControlCmd solutionControlCmd) {
        solutionService.stop(solutionControlCmd.getId());
        return Result.success();
    }
}
