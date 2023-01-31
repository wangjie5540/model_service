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

/**
 * 策略查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY, description = "solutionServingQry")
public interface SolutionServingQryFacade {
    @PostMapping("/solution/solutionServing/pageBy")
    @Operation(summary = "方案服务分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
    Result<PageView<SolutionServingDTO>> pageBy(@RequestBody SolutionServingPageByQry solutionServingPageByQry);

    @PostMapping("/solution/solutionServing/getById")
    @Operation(summary = "根据Id获取方案服务", tags = CommonConst.SWAGGER_TAG_SOLUTION_SERVING_QRY)
    Result<SolutionServingDTO> getById(@RequestBody SolutionServingGetByIdQry solutionServingGetByIdQry);
}
