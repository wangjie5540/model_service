package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.cmd.SceneModifyCmd;
import com.digitforce.aip.dto.data.SceneDynamicFromDTO;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.entity.SceneVersion;
import com.digitforce.aip.enums.SceneStatusEnum;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.aip.mapper.SceneMapper;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.aip.service.ISceneVersionService;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.component.config.api.dto.qry.ConfigQry;
import com.digitforce.component.config.api.facade.qry.ConfigQryFacade;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.framework.tool.ConvertTool;
import com.digitforce.framework.tool.PageTool;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 场景表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
@Service
@Slf4j
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements ISceneService {
    @Resource
    private ConfigQryFacade configQryFacade;
    @Resource
    private ISceneVersionService sceneVersionService;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public PageView<Scene> page(ScenePageByQry scenePageByQry) {
        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>(BeanUtil.toBean(scenePageByQry.getClause(), Scene.class));
        Map<String, Object> map = BeanUtil.beanToMap(scenePageByQry.getLikeClause(), true, true);
        if (!Objects.isNull(map)) {
            map.forEach(queryWrapper::like);
        }
        Page<Scene> page = PageUtil.page(scenePageByQry);
        page = super.page(page, queryWrapper);
        return PageTool.pageView(page);
    }

    @Override
    @SneakyThrows
    @Deprecated
    public SceneDynamicFromDTO getDynamicFormBySceneId(Long sceneId) {
        Scene scene = super.getById(sceneId);
        SceneVersion sceneVersion = sceneVersionService.getById(scene.getVidInUse());
        ConfigQry configQry = new ConfigQry();
        configQry.setSystemCode(CommonConst.SYSTEM_CODE);
        configQry.setConfigKey(sceneVersion.getPipelineName());
        String configValue = configQryFacade.detail(configQry).getData().getConfigValue();
        Yaml yaml = new Yaml();
        Map<String, Object> dynamicForm = yaml.load(configValue);
        String dynamicFormStr = objectMapper.writeValueAsString(dynamicForm);
        return objectMapper.readValue(dynamicFormStr, SceneDynamicFromDTO.class);
    }

    @Override
    @SneakyThrows
    public Map<String, Object> getDynamicForm(Long sceneId, StageEnum type) {
        Scene scene = super.getById(sceneId);
        SceneVersion sceneVersion = sceneVersionService.getById(scene.getVidInUse());
        ConfigQry configQry = new ConfigQry();
        configQry.setSystemCode(CommonConst.SYSTEM_CODE);
        configQry.setConfigKey(StrUtil.format("{}_{}_dynamic", sceneVersion.getPipelineName(), type));
        String configValue = configQryFacade.detail(configQry).getData().getConfigValue();
        Yaml yaml = new Yaml();
        Map<String, Object> dynamicForm = yaml.load(configValue);
        String dynamicFormStr = objectMapper.writeValueAsString(dynamicForm);
        return objectMapper.readValue(dynamicFormStr, new TypeReference<Map<String, Object>>() {
        });
    }

    @Override
    public void updateScene(SceneModifyCmd sceneModifyCmd) {
        Scene scene = getById(sceneModifyCmd.getId());
        if (scene == null) {
            throw new RuntimeException("场景不存在");
        }
        if (scene.getStatus() == SceneStatusEnum.ONLINE) {
            throw new RuntimeException("场景已上线，不能修改");
        }
        scene = ConvertTool.convert(sceneModifyCmd, Scene.class);
        scene.setUpdateUser(TenantContext.tenant().getUserAccount());
        updateById(scene);
        if (sceneModifyCmd.getSceneVersion() != null) {
            SceneVersion sceneVersion = ConvertTool.convert(sceneModifyCmd.getSceneVersion(), SceneVersion.class);
            sceneVersion.setId(scene.getVidInUse());
            sceneVersion.setUpdateUser(TenantContext.tenant().getUserAccount());
            sceneVersionService.updateById(sceneVersion);
        }
    }
}
