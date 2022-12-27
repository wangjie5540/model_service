package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SceneAddCmd;
import com.digitforce.aip.dto.cmd.SceneDeleteCmd;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.cmd.SceneStatusCmd;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SCENE_CMD, description = "sceneCmd")
public interface SceneCmdFacade {
    @PostMapping("/solution/scene/add")
    @Operation(summary = "创建场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    Result add(@RequestBody @Valid SceneAddCmd sceneAddCmd);

    @PostMapping("/solution/scene/delete")
    @Operation(summary = "删除场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    Result delete(@RequestBody @Valid SceneDeleteCmd sceneDeleteCmd);

    @PostMapping("/solution/scene/publish")
    @Operation(summary = "发布场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    Result publish(@RequestBody @Valid SceneStatusCmd sceneStatusCmd);

    @PostMapping("/solution/scene/unPublish")
    @Operation(summary = "取消发布场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    Result unPublish(@RequestBody @Valid SceneStatusCmd sceneStatusCmd);

    @PostMapping("/solution/scene/modify")
    @Operation(summary = "编辑场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    Result modify(@RequestBody @Valid SceneModifyCmd sceneModifyCmd);
}
