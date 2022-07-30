package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.qry.PipelineGetByIdQry;
import com.digitforce.aip.model.Pipeline;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 方案服务查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_PIPELINE_QRY)
@RequestMapping(path = {"/pipeline", "solution/pipeline"})
public interface KubeflowPipelineQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过id获取pipeline", tags = CommonConst.SWAGGER_TAG_PIPELINE_QRY)
    Result<Pipeline> getById(@RequestBody PipelineGetByIdQry pipelineGetByIdQry);

    @PostMapping("/listBy")
    @Operation(summary = "获取pipeline列表", tags = CommonConst.SWAGGER_TAG_PIPELINE_QRY)
    Result<List<Pipeline>> listBy();
}
