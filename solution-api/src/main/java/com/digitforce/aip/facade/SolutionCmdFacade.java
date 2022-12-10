package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionBatchStatusCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * 方案命令接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
public interface SolutionCmdFacade {
    @PostMapping("/solution/add")
    @Operation(summary = "创建方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result add(@RequestBody @Valid SolutionAddCmd solutionAddCmd);

    @PostMapping("/solution/delete")
    @Operation(summary = "删除方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result delete(@RequestBody SolutionDeleteCmd solutionDeleteCmd);

    @PostMapping("/solution/publish")
    @Operation(summary = "发布方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result publish(@RequestBody SolutionPublishCmd solutionPublishCmd);

    @PostMapping("/solution/unPublish")
    @Operation(summary = "取消发布方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result off(@RequestBody SolutionPublishCmd solutionPublishCmd);

    @PostMapping("/solution/execute")
    @Operation(summary = "触发单次执行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result execute(@RequestBody SolutionPublishCmd solutionStatusCmd);

    @PostMapping("/solution/batchExecute")
    @Operation(summary = "批量执行方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result batchExecute(@RequestBody SolutionBatchStatusCmd solutionBatchStatusCmd);

    @PostMapping("/solution/stop")
    @Operation(summary = "触发单次执行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result stop(@RequestBody SolutionPublishCmd solutionStatusCmd);


    @PostMapping("/solution/batchDelete")
    @Operation(summary = "批量删除方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result batchDelete(@RequestBody SolutionBatchStatusCmd solutionBatchStatusCmd);

    @PostMapping("/solution/modifyById")
    @Operation(summary = "编辑方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result modifyById(@RequestBody SolutionModifyCmd solutionModifyCmd);
}
