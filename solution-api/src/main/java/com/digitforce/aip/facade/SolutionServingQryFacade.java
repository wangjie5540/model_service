package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SolutionServingGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 策略查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
@RequestMapping(path = {"/solutionServing", "/solution/solutionServing"})
public interface SolutionServingQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过方案服务id获取详情", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
    Result<SolutionServingDTO> getById(@RequestBody SolutionServingGetByIdQry solutionServingGetByIdQry);

    @PostMapping("/pageBy")
    @Operation(summary = "方案服务分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
    Result<PageView<SolutionServingDTO>> pageBy(@RequestBody SolutionServingPageByQry solutionServingPageByQry);
}
