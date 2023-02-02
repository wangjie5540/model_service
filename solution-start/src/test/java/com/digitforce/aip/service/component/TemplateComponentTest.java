package com.digitforce.aip.service.component;

import com.digitforce.aip.enums.StageEnum;
import com.digitforce.aip.test.BaseTest;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;

import javax.annotation.Resource;
import java.util.Map;

public class TemplateComponentTest extends BaseTest {
    @Resource
    private TemplateComponent templateComponent;
    @Resource
    private FreeMarkerProperties freeMarkerProperties;

    @Test
    public void getPipelineTemplate() {
        String res = templateComponent.getPipelineTemplate("lookalike_zxr", StageEnum.TRAIN);
        System.out.println(res);
    }

    @Test
    public void getPipelineParams() {
        System.out.println(freeMarkerProperties);
        Map<String, Object> map = Maps.newHashMap();
        map.put("event_code_buy", "fund_buy");
        map.put("lookalike__dnn_dropout", 0.03);
        map.put("lookalike__batch_size", 2000);
        map.put("lookalike__lr", 0.1);
        String pipelineParams = templateComponent.getPipelineParams("lookalike", StageEnum.TRAIN, map);
        System.out.println(pipelineParams);
    }
}