package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.aip.dto.qry.SceneGetByIdQry;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    @Resource
    private ISceneService sceneService;

    @Override
    public Result<SceneDTO> listBy() {
        SceneDTO sceneDTO = new SceneDTO();
        sceneDTO.setScenes(sceneList);
        return Result.success(sceneDTO);
    }

    @Override
    public Result<PageView<SceneDTO>> pageBy(ScenePageByQry scenePageByQry) {
        return null;
    }

    @Override
    public Result<SceneDTO> getById(SceneGetByIdQry sceneGetByIdQry) {
        Scene scene = sceneService.getById(sceneGetByIdQry.getId());
        return Result.success(ConvertTool.convert(scene, SceneDTO.class));
    }
}
