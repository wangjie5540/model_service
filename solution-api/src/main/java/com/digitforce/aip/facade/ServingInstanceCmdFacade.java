package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.ServingInstanceAddCmd;
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
@Tag(name = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_CMD, description = "servingInstanceCmd")
public interface ServingInstanceCmdFacade {
    @PostMapping("/solution/servingInstance/add")
    @Operation(summary = "添加服务实例", tags = CommonConst.SWAGGER_TAG_SERVING_INSTANCE_CMD)
    Result add(@RequestBody ServingInstanceAddCmd servingInstanceAddCmd);
}
