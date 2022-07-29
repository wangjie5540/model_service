package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.FunctionDTO;
import com.digitforce.aip.dto.data.RelationDTO;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * filter相关接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_FILTER_QRY)
@RequestMapping(path = "/solution/filter")
public interface FilterQryFacade {
    @PostMapping("/function/listBy")
    @Operation(summary = "获取function列表", tags = CommonConst.SWAGGER_TAG_FILTER_QRY)
    Result<FunctionDTO> functionListBy();

    @PostMapping("/relation/listBy")
    @Operation(summary = "获取relation列表", tags = CommonConst.SWAGGER_TAG_FILTER_QRY)
    Result<RelationDTO> relationListBy();
}
