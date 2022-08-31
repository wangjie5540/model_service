package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.framework.api.dto.Result;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 场景查询实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SceneQryFacadeImpl implements SceneQryFacade {
    // TODO 后续对接字典服务，获取适用系统信息
    private final static List<String> sceneList = Lists.newArrayList("CJ", "CD");

    @Override
    public Result<SceneDTO> listBy() {
        SceneDTO sceneDTO = new SceneDTO();
        sceneDTO.setScenes(sceneList);
        return Result.success(sceneDTO);
    }
}
