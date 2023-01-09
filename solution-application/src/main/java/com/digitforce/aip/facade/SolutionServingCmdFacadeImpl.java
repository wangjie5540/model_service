package com.digitforce.aip.facade;

import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.aip.service.component.TemplateComponent;
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
    @Resource
    private ISolutionService solutionService;
    @Resource
    private TemplateComponent templateComponent;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public Result add(SolutionServingAddCmd solutionServingAddCmd) {
        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
        Solution solution = solutionService.getById(solutionServingAddCmd.getSolutionId());
        if (solution == null) {
            throw new RuntimeException("方案不存在");
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
        return Result.success();
    }
}
