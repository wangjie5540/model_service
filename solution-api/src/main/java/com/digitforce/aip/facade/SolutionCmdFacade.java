package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionControlCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionPublishCmd;
import com.digitforce.aip.dto.cmd.SolutionUnPublishCmd;
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
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_CMD, description = "solutionCmd")
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
    Result unPublish(@RequestBody SolutionUnPublishCmd solutionUnPublishCmd);

    @PostMapping("/solution/modifyById")
    @Operation(summary = "编辑方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result modifyById(@RequestBody SolutionModifyCmd solutionModifyCmd);

    @PostMapping("/solution/startRun")
    @Operation(summary = "开始运行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result startRun(@RequestBody SolutionControlCmd solutionControlCmd);

    @PostMapping("/solution/stopRun")
    @Operation(summary = "停止运行", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result stopRun(@RequestBody SolutionControlCmd solutionControlCmd);
}
