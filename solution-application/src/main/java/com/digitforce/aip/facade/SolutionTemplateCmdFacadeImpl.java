package com.digitforce.aip.facade;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.dto.cmd.*;
import com.digitforce.aip.service.cmd.SolutionTemplateCmdService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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
    private SolutionTemplateCmdService solutionTemplateCmdService;

    @Override
    public Result add(SolutionTemplateAddCmd solutionAddCmd) {
        solutionTemplateCmdService.add(solutionAddCmd);
        return Result.success();
    }

    @Override
    public Result on(SolutionTemplateStatusCmd solutionOnlineCmd) {
        solutionTemplateCmdService.on(solutionOnlineCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchOn(SolutionTemplateBatchStatusCmd solutionTemplateBatchStatusCmd) {
        solutionTemplateCmdService.batchOn(solutionTemplateBatchStatusCmd.getIds());
        return Result.success();
    }

    @Override
    public Result off(SolutionTemplateStatusCmd solutionOnlineCmd) {
        solutionTemplateCmdService.off(solutionOnlineCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchOff(SolutionTemplateBatchStatusCmd solutionTemplateBatchStatusCmd) {
        solutionTemplateCmdService.batchOff(solutionTemplateBatchStatusCmd.getIds());
        return Result.success();
    }

    @Override
    public Result delete(SolutionTemplateDeleteCmd solutionDeleteCmd) {
        solutionTemplateCmdService.delete(solutionDeleteCmd.getId());
        return Result.success();
    }

    @Override
    public Result batchDelete(SolutionTemplateBatchDeleteCmd solutionTemplateBatchDeleteCmd) {
        solutionTemplateCmdService.batchDelete(solutionTemplateBatchDeleteCmd.getIds());
        return Result.success();
    }

    @Override
    public Result modify(SolutionTemplateModifyCmd solutionModifyCmd) {
        solutionTemplateCmdService.modify(solutionModifyCmd);
        return Result.success();
    }

    @Override
    public Result batchModify(SolutionTemplateBatchModifyCmd solutionTemplateBatchModifyCmd) {
        List<SolutionTemplate> solutionTemplateList =
                solutionTemplateBatchModifyCmd.getSolutionTemplateList().stream().map(s -> ConvertTool.convert(s,
                        SolutionTemplate.class)).collect(Collectors.toList());
        solutionTemplateCmdService.batchModify(solutionTemplateList);
        return Result.success();
    }
}
