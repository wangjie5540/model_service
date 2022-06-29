package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.ImplementationDTO;
import com.digitforce.aip.dto.qry.ImplementationGetByIdQry;
import com.digitforce.aip.dto.qry.ImplementationListBySolutionQry;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 策略查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_STRATEGY_QRY)
@RequestMapping(path = "/implementation")
public interface ImplementationQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过id获取实施详情", tags = CommonConst.SWAGGER_TAG_STRATEGY_QRY)
    Result<ImplementationDTO> getById(@RequestBody ImplementationGetByIdQry strategyGetByIdQry);

    @PostMapping("/listBySolutionId")
    @Operation(summary = "通过方案id获取实施列表", tags = CommonConst.SWAGGER_TAG_STRATEGY_QRY)
    Result<List<ImplementationDTO>> listBySolutionId(@RequestBody ImplementationListBySolutionQry implementationListBySolutionQry);
}
