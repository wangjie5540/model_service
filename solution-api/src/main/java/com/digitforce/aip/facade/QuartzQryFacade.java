package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 定时任务调度信息接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_QUARTZ_QRY, description = "quartzQry")
public interface QuartzQryFacade {
    @PostMapping("/solution/quartz/trigger/getTriggerGroups")
    @Operation(summary = "获取触发器组列表", tags = CommonConst.SWAGGER_TAG_QUARTZ_QRY)
    Result<List<String>> getTriggerGroups();

    @PostMapping("/solution/quartz/trigger/list")
    @Operation(summary = "分页查询模型包", tags = CommonConst.SWAGGER_TAG_QUARTZ_QRY)
    Result<Object> triggerPageBy();
}
