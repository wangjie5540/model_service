package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionListBySolutionQry;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 方案查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
@RequestMapping(path = "/solution")
public interface SolutionQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过id获取方案详情", tags = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
    Result<SolutionDTO> getById(@RequestBody SolutionGetByIdQry strategyGetByIdQry);

    @PostMapping("/pageBy")
    @Operation(summary = "方案分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
    Result<PageView<SolutionDTO>> pageBy(@RequestBody SolutionPageByQry solutionPageByQry);

    @PostMapping("/listByTemplateId")
    @Operation(summary = "通过方案模板id分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_QRY)
    Result<List<SolutionDTO>> listBySolutionId(@RequestBody SolutionListBySolutionQry implementationListBySolutionQry);
}
