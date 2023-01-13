package com.digitforce.aip.service.impl;

import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.digitforce.aip.dto.cmd.SolutionAddCmd;
import com.digitforce.aip.entity.Solution;
import com.digitforce.aip.service.ISolutionService;
import com.digitforce.aip.test.BaseTest;
import com.google.common.collect.Maps;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SolutionServiceImplTest extends BaseTest {
    @Resource
    private ISolutionService solutionService;

    @Test
    public void createAndRun() throws InterruptedException {
        SolutionAddCmd solutionAddCmd = new SolutionAddCmd();
        solutionAddCmd.setTitle("lookalike方案");
        solutionAddCmd.setSceneId(6L);
        solutionAddCmd.setSceneName("lookalike测试场景名称");
        solutionAddCmd.setSystem("CD");
        solutionAddCmd.setDescription("lookalike方案描述");
        solutionAddCmd.setPipelineId("cd9a12b9-8407-42bb-bce3-c5bcf166e2c3");
        solutionAddCmd.setPipelineName("lookalike");
        solutionAddCmd.setAutoml(false);
        Map<String, Object> map = Maps.newHashMap();
        map.put("event_code_buy", "fund_buy");
        map.put("sample_select__pos_sample_proportion", 0.5);
        map.put("lookalike__dnn_dropout", 0.2);
        map.put("lookalike__batch_size", 256);
        map.put("lookalike__lr", 0.01);
        solutionAddCmd.setTemplateParams(map);
        solutionService.createAndRun(solutionAddCmd);
        TimeUnit.SECONDS.sleep(1000);
    }

    @Test
    public void updateById() {
        Solution solution = new Solution();

    }

    @Test
    public void pipelineTemplateTest() {
        TemplateEngine engine = TemplateUtil.createEngine();
        Template template = engine.getTemplate("{\n"
                + "    \"sample_select\": {\n"
                + "        \"event_code_buy\": \"${event_code_buy}\",\n"
                + "        \"pos_sample_proportion\": ${sample_select__pos_sample_proportion}\n"
                + "    },\n"
                + "    \"feature_create\": {\n"
                + "        \"event_code_buy\": \"${event_code_buy}\"\n"
                + "    },\n"
                + "    \"lookalike\": {\n"
                + "        \"dnn_dropout\": ${lookalike__dnn_dropout},\n"
                + "        \"batch_size\": ${lookalike__batch_size},\n"
                + "        \"lr\": ${lookalike__lr}\n"
                + "    }\n"
                + "}");
        Map<String, Object> map = Maps.newHashMap();
        map.put("event_code_buy", "buy");
        map.put("sample_select__pos_sample_proportion", 0.5);
        map.put("lookalike__dnn_dropout", 0.5);
        map.put("lookalike__batch_size", 256);
        map.put("lookalike__lr", 0.01);
        String render = template.render(map);
        System.out.println(render);
    }

    @Test
    public void list() {
        List<Solution> list = solutionService.list();
        System.out.println(list);
    }
}