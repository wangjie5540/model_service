package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionRunDTO;
import com.digitforce.aip.dto.qry.SolutionRunLatestVersionQry;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 方案执行查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_RUN_QRY, description = "solutionRunQry")
public interface SolutionRunQryFacade {

    @PostMapping("/solution/solutionRun/pageBy")
    @Operation(summary = "执行结果分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_RUN_QRY)
    Result<PageView<SolutionRunDTO>> pageBy(@RequestBody SolutionRunPageByQry solutionRunPageByQry);

    @PostMapping("/solution/solutionRun/getLatestVersion")
    @Operation(summary = "获取模型最新版本", tags = CommonConst.SWAGGER_TAG_SOLUTION_RUN_QRY)
    Result<SolutionRunDTO> getLatestVersion(@RequestBody SolutionRunLatestVersionQry solutionRunLatestVersionQry);
}
