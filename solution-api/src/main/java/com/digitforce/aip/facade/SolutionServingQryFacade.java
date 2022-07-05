package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionServingGetByIdQry;
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
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
@RequestMapping(path = "/solutionServing")
public interface SolutionServingQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过方案服务id获取详情", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
    Result<SolutionDTO> getById(@RequestBody SolutionServingGetByIdQry solutionServingGetByIdQry);
}
