package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionAddCronCmd;
import com.digitforce.aip.dto.cmd.SolutionBatchStatusCmd;
import com.digitforce.aip.dto.cmd.SolutionClearCronCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionStatusCmd;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 方案命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
@RequestMapping(path = "/solution")
public interface SolutionCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "根据模板创建方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result add(@RequestBody SolutionAddCmd solutionAddCmd);

    @PostMapping("/on")
    @Operation(summary = "上线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result on(@RequestBody SolutionStatusCmd solutionStatusCmd);

    @PostMapping("/batchOn")
    @Operation(summary = "批量上线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result batchOn(@RequestBody SolutionBatchStatusCmd solutionBatchStatusCmd);

    @PostMapping("/off")
    @Operation(summary = "下线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result off(@RequestBody SolutionStatusCmd solutionOnOffCmd);

    @PostMapping("/batchOff")
    @Operation(summary = "批量下线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result batchOff(@RequestBody SolutionBatchStatusCmd solutionBatchStatusCmd);

    @PostMapping("/addCron")
    @Operation(summary = "添加调度", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result addCron(@RequestBody SolutionAddCronCmd solutionAddCronCmd);

    @PostMapping("/clearCron")
    @Operation(summary = "清除调度", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result clearCron(@RequestBody SolutionClearCronCmd solutionClearCronCmd);

    @PostMapping("/execute")
    @Operation(summary = "触发单次执行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result execute(@RequestBody SolutionStatusCmd solutionStatusCmd);

    @PostMapping("/batchExecute")
    @Operation(summary = "批量执行方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result batchExecute(@RequestBody SolutionBatchStatusCmd solutionBatchStatusCmd);

    @PostMapping("/stop")
    @Operation(summary = "触发单次执行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result stop(@RequestBody SolutionStatusCmd solutionStatusCmd);

    @PostMapping("/delete")
    @Operation(summary = "删除方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result delete(@RequestBody SolutionDeleteCmd solutionDeleteCmd);

    @PostMapping("/batchDelete")
    @Operation(summary = "批量删除方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result batchDelete(@RequestBody SolutionBatchStatusCmd solutionBatchStatusCmd);

    @PostMapping("/modifyById")
    @Operation(summary = "编辑方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result modify(@RequestBody SolutionModifyCmd solutionModifyCmd);
}
