package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SceneAddCmd;
import com.digitforce.aip.dto.cmd.SceneDeleteCmd;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.cmd.ScenePublishCmd;
import com.digitforce.aip.dto.cmd.SceneUnPublishCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SCENE_CMD)
public interface SceneCmdFacade {
    @PostMapping("/solution/scene/add")
    @Operation(summary = "创建场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    void add(@RequestBody @Valid SceneAddCmd sceneAddCmd);

    @PostMapping("/solution/scene/delete")
    @Operation(summary = "删除场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    void delete(@RequestBody @Valid SceneDeleteCmd sceneDeleteCmd);

    @PostMapping("/solution/scene/publish")
    @Operation(summary = "发布场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    void publish(@RequestBody @Valid ScenePublishCmd scenePublishCmd);

    @PostMapping("/solution/scene/unPublish")
    @Operation(summary = "取消发布场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    void unPublish(@RequestBody @Valid SceneUnPublishCmd sceneUnPublishCmd);

    @PostMapping("/solution/scene/modify")
    @Operation(summary = "编辑场景", tags = CommonConst.SWAGGER_TAG_SCENE_CMD)
    void modify(@RequestBody @Valid SceneModifyCmd sceneModifyCmd);
}
