package com.digitforce.aip.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.data.SceneDynamicFromDTO;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.framework.api.dto.PageView;

/**
 * <p>
 * 场景表 服务类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
public interface ISceneService extends IService<Scene> {
    PageView<Scene> page(ScenePageByQry scenePageByQry);

    SceneDynamicFromDTO getDynamicFormBySceneId(Long sceneId);

    Object getDynamicForm(Long sceneId, StageEnum type);

    void updateScene(SceneModifyCmd sceneModifyCmd);
}
