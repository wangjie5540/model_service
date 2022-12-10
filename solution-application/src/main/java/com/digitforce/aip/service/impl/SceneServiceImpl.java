package com.digitforce.aip.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.digitforce.aip.dto.qry.ScenePageByQry;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.mapper.SceneMapper;
import com.digitforce.aip.service.ISceneService;
import com.digitforce.aip.utils.PageUtil;
import com.digitforce.framework.api.dto.PageView;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 场景表 服务实现类
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
@Service
public class SceneServiceImpl extends ServiceImpl<SceneMapper, Scene> implements ISceneService {
    @Override
    public PageView<Scene> page(ScenePageByQry scenePageByQry) {
        QueryWrapper<Scene> queryWrapper = new QueryWrapper<>(BeanUtil.toBean(scenePageByQry.getClause(), Scene.class));
        Map<String, Object> map = BeanUtil.beanToMap(scenePageByQry.getLikeClause(), false, true);
        map.forEach(queryWrapper::like);
        Page<Scene> page = PageUtil.page(scenePageByQry);
        page = super.page(page, queryWrapper);
        return PageView.of((int) page.getTotal(), page.getRecords());
    }
}
