package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.dto.cmd.SolutionDeleteCmd;
import com.digitforce.aip.dto.cmd.SolutionModifyCmd;
import com.digitforce.aip.dto.cmd.SolutionOnlineCmd;
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
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
public interface SolutionCmdFacade {
    @PostMapping("/add")
    @Operation(summary = "新增方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    Result add(@RequestBody SolutionAddCmd solutionAddCmd);

    @Operation(summary = "上线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    @PostMapping("/on")
    Result on(@RequestBody SolutionOnlineCmd solutionOnlineCmd);

    @Operation(summary = "下线方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    @PostMapping("/off")
    Result off(@RequestBody SolutionOnlineCmd solutionOnlineCmd);

    @Operation(summary = "新增方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    @PostMapping("/delete")
    Result delete(@RequestBody SolutionDeleteCmd solutionDeleteCmd);

    @Operation(summary = "修改方案", tags = CommonConst.SWAGGER_TAG_SOLUTION_CMD)
    @PostMapping("/modify")
    Result modify(@RequestBody SolutionModifyCmd solutionModifyCmd);
}
