package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 场景查询类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SCENE_QRY)
@RequestMapping(path = "/scene")
public interface SceneQryFacade {
    @PostMapping("/listBy")
    @Operation(summary = "获取适用系统列表", tags = CommonConst.SWAGGER_TAG_SCENE_QRY)
    Result<SceneDTO> listBy();
}
