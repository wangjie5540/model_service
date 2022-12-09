package com.digitforce.aip.facade;


import com.digitforce.aip.dto.cmd.SceneAddCmd;
import com.digitforce.aip.dto.cmd.SceneDeleteCmd;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.cmd.ScenePublishCmd;
import com.digitforce.aip.dto.cmd.SceneUnPublishCmd;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SceneCmdFacadeImpl implements SceneCmdFacade {
    @Override
    public void add(SceneAddCmd sceneAddCmd) {
        // TODO add
    }

    @Override
    public void delete(SceneDeleteCmd sceneDeleteCmd) {
        // TODO delete
    }

    @Override
    public void publish(ScenePublishCmd scenePublishCmd) {
        // TODO
    }

    @Override
    public void unPublish(SceneUnPublishCmd sceneUnPublishCmd) {
        // TODO
    }

    @Override
    public void modify(SceneModifyCmd sceneModifyCmd) {
        // TODO
    }
}
