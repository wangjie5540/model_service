package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.ServingInstanceDTO;
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
}
