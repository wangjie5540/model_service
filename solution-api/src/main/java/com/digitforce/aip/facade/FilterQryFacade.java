package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.FunctionDTO;
import com.digitforce.aip.dto.data.RelationDTO;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * filter相关接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_FILTER_QRY, description = "filterQry")
public interface FilterQryFacade {
    @PostMapping("/solution/filter/function/listBy")
    @Operation(summary = "获取function列表", tags = CommonConst.SWAGGER_TAG_FILTER_QRY)
    Result<FunctionDTO> functionListBy();

    @PostMapping("/solution/filter/relation/listBy")
    @Operation(summary = "获取relation列表", tags = CommonConst.SWAGGER_TAG_FILTER_QRY)
    Result<RelationDTO> relationListBy();
}
