package com.digitforce.aip.facade;


import com.digitforce.aip.dto.cmd.SceneAddCmd;
import com.digitforce.aip.dto.cmd.SceneDeleteCmd;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.cmd.SceneStatusCmd;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.entity.SceneVersion;
import com.digitforce.aip.enums.SceneStatusEnum;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.aip.service.ISceneVersionService;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.tool.ConvertTool;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SceneCmdFacadeImpl implements SceneCmdFacade {
    @Resource
    private ISceneService sceneService;
    @Resource
    private ISceneVersionService sceneVersionService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result add(SceneAddCmd sceneAddCmd) {
        Scene scene = ConvertTool.convert(sceneAddCmd, Scene.class);
        scene.setCreateUser(TenantContext.tenant().getUserAccount());
        scene.setUpdateUser(TenantContext.tenant().getUserAccount());
        // 添加版本
        SceneVersion sceneVersion = ConvertTool.convert(sceneAddCmd, SceneVersion.class);
        sceneVersion.setCreateUser(TenantContext.tenant().getUserAccount());
        sceneVersion.setUpdateUser(TenantContext.tenant().getUserAccount());
        sceneVersionService.save(sceneVersion);
        // 添加场景
        scene.setVidInUse(sceneVersion.getId());
        sceneService.save(scene);
        return Result.success();
    }

    @Override
    public Result delete(SceneDeleteCmd sceneDeleteCmd) {
        Scene scene = sceneService.getById(sceneDeleteCmd.getId());
        if (scene == null) {
            throw new RuntimeException("场景不存在");
        }
        if (scene.getStatus() == SceneStatusEnum.ONLINE) {
            throw new RuntimeException("场景已上线，不能删除");
        }
        sceneService.removeById(sceneDeleteCmd.getId());
        return Result.success();
    }

    @Override
    public Result publish(SceneStatusCmd sceneStatusCmd) {
        Scene scene = ConvertTool.convert(sceneStatusCmd, Scene.class);
        scene.setStatus(SceneStatusEnum.ONLINE);
        scene.setUpdateUser(TenantContext.tenant().getUserAccount());
        sceneService.updateById(scene);
        return Result.success();
    }

    @Override
    public Result unPublish(SceneStatusCmd sceneStatusCmd) {
        Scene scene = ConvertTool.convert(sceneStatusCmd, Scene.class);
        scene.setStatus(SceneStatusEnum.OFFLINE);
        scene.setUpdateUser(TenantContext.tenant().getUserAccount());
        sceneService.updateById(scene);
        return Result.success();
    }

    @Override
    public Result modify(SceneModifyCmd sceneModifyCmd) {
        Scene scene = ConvertTool.convert(sceneModifyCmd, Scene.class);
        scene.setUpdateUser(TenantContext.tenant().getUserAccount());
        sceneService.updateById(scene);
        return Result.success();
    }
}
