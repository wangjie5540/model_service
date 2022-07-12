package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.*;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 方案模板命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
@RequestMapping(path = "/solutionTemplate")
public interface SolutionTemplateCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "创建模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    Result add(@RequestBody SolutionTemplateAddCmd solutionAddCmd);

    @Operation(summary = "上线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/on")
    Result on(@RequestBody SolutionTemplateStatusCmd solutionTemplateStatusCmd);

    @Operation(summary = "批量上线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/batchOn")
    Result batchOn(@RequestBody SolutionTemplateBatchStatusCmd solutionTemplateBatchStatusCmd);

    @Operation(summary = "下线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/off")
    Result off(@RequestBody SolutionTemplateStatusCmd solutionTemplateStatusCmd);

    @Operation(summary = "批量下线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/batchOff")
    Result batchOff(@RequestBody SolutionTemplateBatchStatusCmd solutionTemplateBatchStatusCmd);

    @Operation(summary = "删除模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/delete")
    Result delete(@RequestBody SolutionTemplateDeleteCmd solutionTemplateDeleteCmd);

    @Operation(summary = "批量删除模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/batchDelete")
    Result batchDelete(@RequestBody SolutionTemplateBatchDeleteCmd solutionTemplateBatchDeleteCmd);

    @Operation(summary = "修改模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/modify")
    Result modify(@RequestBody SolutionTemplateModifyCmd solutionModifyCmd);

    @Operation(summary = "批量修改模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/batchModify")
    Result batchModify(@RequestBody SolutionTemplateBatchModifyCmd solutionTemplateBatchModifyCmd);
}
