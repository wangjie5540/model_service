package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.ImplementationAddCmd;
import com.digitforce.aip.dto.cmd.ImplementationTriggerCmd;
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
@RequestMapping(path = "/implementation")
public interface ImplementationCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "新增实施", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result add(@RequestBody ImplementationAddCmd implementAddCmd);

    @PostMapping("/trigger_run")
    @Operation(summary = "新增实施", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result triggerRun(@RequestBody ImplementationTriggerCmd implementationTriggerCmd);
}
