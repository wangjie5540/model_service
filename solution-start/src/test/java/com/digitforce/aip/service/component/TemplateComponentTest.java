package com.digitforce.aip.service.component;

import com.digitforce.aip.enums.StageEnum;
import com.digitforce.aip.test.BaseTest;
import org.junit.Test;

import javax.annotation.Resource;

public class TemplateComponentTest extends BaseTest {
    @Resource
    private TemplateComponent templateComponent;

    @Test
    public void getPipelineTemplate() {
        String res = templateComponent.getPipelineTemplate("lookalike_zxr", StageEnum.TRAIN);
        System.out.println(res);
    }
}