package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.PredictDetailDTO;
import com.digitforce.aip.dto.data.PredictResultDTO;
import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.dto.qry.GetAleQry;
import com.digitforce.aip.dto.qry.GetPredictDetailByIdQry;
import com.digitforce.aip.dto.qry.GetPredictResultQry;
import com.digitforce.aip.dto.qry.GetShapleyQry;
import com.digitforce.aip.dto.qry.PredictDetailPageByQry;
import com.digitforce.aip.dto.qry.ServingInstanceGetByIdQry;
import com.digitforce.aip.dto.qry.ServingInstancePageByQry;
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
@Tag(name = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY, description = "servingInstanceQry")
public interface ServingInstanceQryFacade {
    @PostMapping("/solution/servingInstance/pageBy")
    @Operation(summary = "服务实例分页查询", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<PageView<ServingInstanceDTO>> pageBy(@RequestBody ServingInstancePageByQry servingInstancePageByQry);

    @PostMapping("/solution/servingInstance/getById")
    @Operation(summary = "通过id查询服务实例", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<ServingInstanceDTO> getById(@RequestBody ServingInstanceGetByIdQry servingInstanceGetByIdQry);

    @PostMapping("/solution/servingInstance/getPredictStatistics")
    @Operation(summary = "获取预测结果", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<PredictResultDTO> getPredictStatistics(@RequestBody GetPredictResultQry getPredictResultQry);

    @PostMapping("/solution/servingInstance/pageByPredictDetail")
    @Operation(summary = "获取预测明细分页", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<PageView<PredictDetailDTO>> pageByPredictDetail(@RequestBody PredictDetailPageByQry predictResultPageByQry);

    @PostMapping("/solution/servingInstance/getPredictDetailById")
    @Operation(summary = "通过用户id获取预测明细", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<PredictDetailDTO> getPredictDetailById(@RequestBody GetPredictDetailByIdQry getPredictDetailByIdQry);

    @PostMapping("/solution/servingInstance/getAle")
    @Operation(summary = "获取ale", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<Object> getAle(@RequestBody GetAleQry getAleQry);

    @PostMapping("/solution/servingInstance/getShapley")
    @Operation(summary = "获取shapley值", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_QRY)
    Result<Object> getShapley(@RequestBody GetShapleyQry getShapleyQry);
}
