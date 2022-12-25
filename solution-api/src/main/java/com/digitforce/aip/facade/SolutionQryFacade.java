package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 方案查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
public interface SolutionQryFacade {
    @PostMapping("/solution/getById")
    @Operation(summary = "通过id获取方案详情", tags = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
    Result<SolutionDTO> getById(@RequestBody SolutionGetByIdQry strategyGetByIdQry);

    @PostMapping("/solution/pageBy")
    @Operation(summary = "方案分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
    Result<PageView<SolutionDTO>> pageBy(@RequestBody SolutionPageByQry solutionPageByQry);
}
