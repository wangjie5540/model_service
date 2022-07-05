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
 * 策略命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
@RequestMapping(path = "/solution")
public interface SolutionCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "根据模板创建方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result add(@RequestBody SolutionAddCmd implementAddCmd);

    @PostMapping("/on")
    @Operation(summary = "上线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result on(@RequestBody SolutionAddCmd implementAddCmd);

    @PostMapping("/off")
    @Operation(summary = "下线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result off(@RequestBody SolutionOnOffCmd solutionOnOffCmd);

    @PostMapping("/addCron")
    @Operation(summary = "添加调度", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result addCron(@RequestBody SolutionAddCronCmd solutionAddCronCmd);

    @PostMapping("/clearCron")
    @Operation(summary = "清除调度", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result clearCron(@RequestBody SolutionClearCronCmd solutionClearCronCmd);

    @PostMapping("/trigger_run")
    @Operation(summary = "触发单次执行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result triggerRun(@RequestBody SolutionTriggerCmd implementationTriggerCmd);
}
