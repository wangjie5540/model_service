package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.aip.dto.qry.SceneGetByIdQry;
import com.digitforce.aip.dto.qry.SceneGetFromQry;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 场景查询类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SCENE_QRY, description = "sceneQry")
public interface SceneQryFacade {
    @PostMapping("/solution/scene/pageBy")
    @Operation(summary = "分页查询场景", tags = CommonConst.SWAGGER_TAG_SCENE_QRY)
    Result<PageView<SceneDTO>> pageBy(@RequestBody ScenePageByQry scenePageByQry);

    @PostMapping("/solution/scene/getById")
    @Operation(summary = "根据id获取场景信息", tags = CommonConst.SWAGGER_TAG_SCENE_QRY)
    Result<SceneDTO> getById(@RequestBody SceneGetByIdQry sceneGetByIdQry);

    @PostMapping("/solution/scene/getDynamicForm")
    @Operation(summary = "获取动态表单", tags = CommonConst.SWAGGER_TAG_SCENE_QRY)
    Result<Map<String, Object>> getDynamicForm(@RequestBody SceneGetFromQry sceneGetFromQry);
}
