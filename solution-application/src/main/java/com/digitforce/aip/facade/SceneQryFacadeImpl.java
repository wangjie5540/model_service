package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.aip.dto.data.SceneDynamicFromDTO;
import com.digitforce.aip.dto.data.SceneVersionDTO;
import com.digitforce.aip.dto.qry.SceneGetByIdQry;
import com.digitforce.aip.dto.qry.SceneGetFromQry;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.entity.SceneVersion;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.aip.service.ISceneVersionService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 场景查询实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
@Slf4j
public class SceneQryFacadeImpl implements SceneQryFacade {
    @Resource
    private ISceneService sceneService;
    @Resource
    private ISceneVersionService sceneVersionService;

    @Override
    public Result<PageView<SceneDTO>> pageBy(ScenePageByQry scenePageByQry) {
        PageView<Scene> pageView = sceneService.page(scenePageByQry);
        if (pageView.getCount() == 0) {
            return Result.success(PageView.of(0, Lists.newArrayList()));
        }
        List<Long> sceneIds = pageView.getList().stream().map(Scene::getVidInUse).collect(Collectors.toList());
        List<SceneVersion> sceneVersions = sceneVersionService.listByIds(sceneIds);
        PageView<SceneDTO> sceneDTOPageView = PageTool.pageView(pageView, SceneDTO.class);
        for (int i = 0; i < sceneVersions.size(); i++) {
            SceneVersion sceneVersion = sceneVersions.get(i);
            SceneDTO sceneDTO = sceneDTOPageView.getList().get(i);
            SceneVersionDTO sceneVersionDTO = ConvertTool.convert(sceneVersion, SceneVersionDTO.class);
            sceneDTO.setVersionInUse(sceneVersionDTO);
        }
        return Result.success(sceneDTOPageView);
    }

    @Override
    public Result<SceneDTO> getById(SceneGetByIdQry sceneGetByIdQry) {
        Scene scene = sceneService.getById(sceneGetByIdQry.getId());
        SceneVersion sceneVersion = sceneVersionService.getById(scene.getVidInUse());
        SceneVersionDTO sceneVersionDTO = ConvertTool.convert(sceneVersion, SceneVersionDTO.class);
        SceneDTO sceneDTO = ConvertTool.convert(scene, SceneDTO.class);
        sceneDTO.setVersionInUse(sceneVersionDTO);
        return Result.success(sceneDTO);
    }

    @Override
    public Result<SceneDynamicFromDTO> getDynamicFormBySceneId(SceneGetFromQry sceneGetFromQry) {
        SceneDynamicFromDTO sceneDynamicFromDTO = sceneService.getDynamicFormBySceneId(sceneGetFromQry.getSceneId());
        return Result.success(sceneDynamicFromDTO);
    }
}
