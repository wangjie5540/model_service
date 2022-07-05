package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionTemplateAddCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionTemplateOnlineCmd;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 方案服务命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
@RequestMapping(path = "/solutionTemplate")
public interface SolutionTemplateCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "新增模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    Result add(@RequestBody SolutionTemplateAddCmd solutionAddCmd);

    @Operation(summary = "上线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/on")
    Result on(@RequestBody SolutionTemplateOnlineCmd solutionOnlineCmd);

    @Operation(summary = "下线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/off")
    Result off(@RequestBody SolutionTemplateOnlineCmd solutionOnlineCmd);

    @Operation(summary = "删除模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/delete")
    Result delete(@RequestBody SolutionTemplateDeleteCmd solutionDeleteCmd);

    @Operation(summary = "修改模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_CMD)
    @PostMapping("/modify")
    Result modify(@RequestBody SolutionTemplateModifyCmd solutionModifyCmd);
}
