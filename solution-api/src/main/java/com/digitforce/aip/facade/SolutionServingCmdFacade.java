package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.dto.data.SolutionServingDTO;
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
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_CMD)
@RequestMapping(path = "/solutionServing")
public interface SolutionServingCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "添加方案服务", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_CMD)
    Result<SolutionServingDTO> add(@RequestBody SolutionServingAddCmd implementAddCmd);
}
