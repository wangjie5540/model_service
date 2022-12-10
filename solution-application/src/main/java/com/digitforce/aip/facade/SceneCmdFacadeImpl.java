package com.digitforce.aip.facade;


import com.digitforce.aip.dto.cmd.SceneAddCmd;
import com.digitforce.aip.dto.cmd.SceneDeleteCmd;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.cmd.SceneStatusCmd;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SceneCmdFacadeImpl implements SceneCmdFacade {
    @Override
    public Result add(SceneAddCmd sceneAddCmd) {
        // TODO add
        return Result.success();
    }

    @Override
    public Result delete(SceneDeleteCmd sceneDeleteCmd) {
        // TODO delete
        return Result.success();
    }

    @Override
    public Result publish(SceneStatusCmd sceneStatusCmd) {
        // TODO
        return Result.success();
    }

    @Override
    public Result unPublish(SceneStatusCmd sceneStatusCmd) {
        // TODO
        return Result.success();
    }

    @Override
    public Result modify(SceneModifyCmd sceneModifyCmd) {
        // TODO
        return Result.success();
    }
}
