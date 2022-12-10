package com.digitforce.aip.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.digitforce.aip.entity.Scene;
import com.digitforce.aip.enums.SceneTypeEnum;
import com.digitforce.aip.service.ISceneService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SceneServiceImplTest {
    @Resource
    private ISceneService sceneService;

    @Test
    public void save() {
        Scene scene = new Scene();
        scene.setVidInUse(1L);
        scene.setName("test");
        scene.setSceneType(SceneTypeEnum.CUSTOMER_AI);
        scene.setCreateUser("mine");
        scene.setUpdateUser("mine");
        sceneService.save(scene);
    }

    @Test
    public void getById() {
        Scene scene = sceneService.getById(1L);
        Assert.assertNotNull(scene);
    }

    @Test
    public void pageBy() {
        Scene scene = new Scene();
        scene.setName("test");
        PageDTO<Scene> page = new PageDTO<>(1, 10);
        page = sceneService.page(page, new QueryWrapper<>(scene).like("name", "test"));
        Assert.assertNotNull(page);
    }
}