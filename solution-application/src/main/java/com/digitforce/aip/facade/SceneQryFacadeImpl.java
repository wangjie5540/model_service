package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.aip.dto.data.SceneTypeDesc;
import com.digitforce.aip.dto.data.SceneVersionDTO;
import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.aip.dto.qry.SceneGetByIdQry;
import com.digitforce.aip.dto.qry.SceneGetFromQry;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.aip.dto.qry.SceneSearchQry;
import com.digitforce.aip.dto.qry.SolutionPageByQry;
import com.digitforce.aip.dto.qry.SolutionServingPageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.entity.SceneVersion;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.entity.SolutionServing;
import com.digitforce.aip.enums.SceneTypeEnum;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.aip.service.ISceneVersionService;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.service.ISolutionServingService;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    @Resource
    private ISolutionService solutionService;

    @Resource
    private ISolutionServingService solutionServingService;


    @Override
    public Result<PageView<SceneDTO>> pageBy(ScenePageByQry scenePageByQry) {
        PageView<Scene> pageView = sceneService.page(scenePageByQry);
        if (pageView.getCount() == 0) {
            return Result.success(PageView.of(0, Lists.newArrayList()));
        }
        List<Long> versionIds = pageView.getList().stream().map(Scene::getVidInUse).collect(Collectors.toList());
        List<SceneVersion> sceneVersions = sceneVersionService.listByIds(versionIds);
        // 将sceneVersions转换为map
        Map<Long, SceneVersion> sceneVersionMap = sceneVersions.stream()
                .collect(Collectors.toMap(SceneVersion::getId, sceneVersion -> sceneVersion));
        PageView<SceneDTO> sceneDTOPageView = PageTool.pageView(pageView, SceneDTO.class);
        // 组织sceneDTO的map
        Map<Long, SceneDTO> sceneDTOMap = sceneDTOPageView.getList().stream()
                .collect(Collectors.toMap(SceneDTO::getId, sceneDTO -> sceneDTO));
        for (Scene scene : pageView.getList()) {
            SceneVersion sceneVersion = sceneVersionMap.get(scene.getVidInUse());
            SceneDTO sceneDTO = sceneDTOMap.get(scene.getId());
            SceneVersionDTO sceneVersionDTO = ConvertTool.convert(sceneVersion, SceneVersionDTO.class);
            // TODO 目前产品端尚未设计版本的概念，所以这里默认版本为1
            sceneVersionDTO.setId(1L);
            sceneDTO.setVersionInUse(sceneVersionDTO);
        }
        return Result.success(sceneDTOPageView);
    }

    @Override
    public Result<SceneDTO> getById(SceneGetByIdQry sceneGetByIdQry) {
        Scene scene = sceneService.getById(sceneGetByIdQry.getId());
        SceneVersion sceneVersion = sceneVersionService.getById(scene.getVidInUse());
        SceneVersionDTO sceneVersionDTO = ConvertTool.convert(sceneVersion, SceneVersionDTO.class);
        // TODO 目前产品端尚未设计版本的概念，所以这里默认版本为1
        sceneVersionDTO.setId(1L);
        SceneDTO sceneDTO = ConvertTool.convert(scene, SceneDTO.class);
        sceneDTO.setVersionInUse(sceneVersionDTO);
        return Result.success(sceneDTO);
    }

    @Override
    public Result<Object> getDynamicForm(SceneGetFromQry sceneGetFromQry) {
        Object form = sceneService.getDynamicForm(sceneGetFromQry.getSceneId(), sceneGetFromQry.getType());
        return Result.success(form);
    }

    @Override
    public Result<Object> listSceneTypeDesc() {
        return Result.success(Arrays.stream(SceneTypeEnum.values()).map(
                sceneTypeEnum -> new SceneTypeDesc(sceneTypeEnum.getName(), sceneTypeEnum.getCname())
        ).collect(Collectors.toList()));
    }

    @Override
    public Result<List<SolutionDTO>> searchModelOrServing(SceneSearchQry sceneSearchQry) {
        // 获取方案&模型分页数据
        SolutionPageByQry solutionDTOPageQuery = new SolutionPageByQry();
        SolutionDTO solutionLikeClause = new SolutionDTO();
        solutionLikeClause.setTitle(sceneSearchQry.getTitleLike());
        solutionDTOPageQuery.setLikeClause(solutionLikeClause);
        solutionDTOPageQuery.setPageSize(-1);
        PageView<Solution> solutionDTOPageView = solutionService.page(solutionDTOPageQuery);
        List<SolutionDTO> solutionDTOList = ConvertTool.convert(solutionDTOPageView.getList(), SolutionDTO.class);

        // 获取方案服务分页数据
        SolutionServingPageByQry solutionServingPageByQry = new SolutionServingPageByQry();
        SolutionServingDTO solutionServingLikeClause = new SolutionServingDTO();
        solutionServingLikeClause.setTitle(sceneSearchQry.getTitleLike());
        solutionServingPageByQry.setLikeClause(solutionServingLikeClause);
        solutionServingPageByQry.setPageSize(-1);
        PageView<SolutionServing> solutionServingPageView = solutionServingService.page(solutionServingPageByQry);
        List<SolutionServingDTO> servingDTOList = ConvertTool.convert(solutionServingPageView.getList(),
                SolutionServingDTO.class);
        Map<Long, List<SolutionServingDTO>> solutionId2ServingListMap = servingDTOList.stream()
                .collect(Collectors.groupingBy(SolutionServingDTO::getSolutionId));

        solutionDTOList.forEach(solutionDTO -> solutionDTO.setServings(solutionId2ServingListMap.get(solutionDTO.getId())));
        return Result.success(solutionDTOList);
    }
}
