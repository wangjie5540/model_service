package com.digitforce.aip.facade;

import com.digitforce.aip.consts.SolutionErrorCode;
import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.dto.cmd.SolutionServingDeleteCmd;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.aip.mapper.SceneMapper;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.aip.service.component.TemplateComponent;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.api.exception.BizException;
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
    @Resource
    private ISolutionService solutionService;
    @Resource
    private TemplateComponent templateComponent;
    @Resource
    private SceneMapper sceneMapper;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<SolutionServingDTO> add(SolutionServingAddCmd solutionServingAddCmd) {
        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
        Solution solution = solutionService.getById(solutionServingAddCmd.getSolutionId());
        if (solution == null) {
            throw BizException.of(SolutionErrorCode.SOLUTION_NOT_FOUND);
        }
        if (solution.getStatus() != SolutionStatusEnum.PUBLISHED) {
            throw BizException.of(SolutionErrorCode.SOLUTION_NOT_PUBLISHED);
        }
        solutionServing.setPipelineId(solution.getPipelineId());
        solutionServing.setPipelineName(solution.getPipelineName());
        solutionServing.setPipelineTemplate(
                templateComponent.getPipelineTemplate(solution.getPipelineName(), StageEnum.SERVING));
        solutionServing.setTemplateParams(solutionServingAddCmd.getTemplateParams());
        solutionServing.setSceneName(solution.getSceneName());
        solutionServing.setSceneType(solution.getSceneType());
        solutionServing.setSolutionTitle(solution.getTitle());
        solutionServing.setCreateUser(TenantContext.tenant().getUserAccount());
        solutionServing.setUpdateUser(TenantContext.tenant().getUserAccount());
        solutionServingService.save(solutionServing);
        // 增加统计数量
        sceneMapper.increaseServingCount(solution.getSceneId());
        SolutionServingDTO solutionServingDTO = ConvertTool.convert(solutionServing, SolutionServingDTO.class);
        return Result.success(solutionServingDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<Void> delete(SolutionServingDeleteCmd solutionServingDeleteCmd) {
        SolutionServing solutionServing = solutionServingService.getById(solutionServingDeleteCmd.getServingId());
        if (solutionServing == null) {
            throw BizException.of(SolutionErrorCode.SOLUTION_SERVING_NOT_FOUND);
        }
        solutionServingService.removeById(solutionServingDeleteCmd.getServingId());
        sceneMapper.increaseServingCount(solutionServing.getSceneId());
        return Result.success(null);
    }
}
