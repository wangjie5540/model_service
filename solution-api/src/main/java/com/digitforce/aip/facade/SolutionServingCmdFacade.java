package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.dto.cmd.SolutionServingDeleteCmd;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 方案服务命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_CMD, description = "solutionServingCmd")
public interface SolutionServingCmdFacade {
    @PostMapping("/solution/solutionServing/add")
    @Operation(summary = "添加方案服务", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_CMD)
    Result add(@RequestBody SolutionServingAddCmd solutionServingAddCmd);

    @PostMapping("/solution/solutionServing/delete")
    @Operation(summary = "删除方案服务", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_CMD)
    Result delete(@RequestBody SolutionServingDeleteCmd solutionServingDeleteCmd);
}
