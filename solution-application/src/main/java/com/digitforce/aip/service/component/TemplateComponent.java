package com.digitforce.aip.service.component;

import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.template.Template;
import cn.hutool.extra.template.TemplateEngine;
import cn.hutool.extra.template.TemplateUtil;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.component.config.api.dto.data.ConfigItemDTO;
import com.digitforce.component.config.api.dto.qry.ConfigQry;
import com.digitforce.component.config.api.facade.qry.ConfigQryFacade;
import com.digitforce.framework.api.dto.Result;
import org.springframework.stereotype.Component;

import java.util.Map;

import javax.annotation.Resource;

/**
 * 模板组件
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2023/01/07 17:32
 */
@Component
public class TemplateComponent {
    @Resource
    private ConfigQryFacade configQryFacade;

    public String getPipelineParams(String pipelineName, StageEnum stage, Map<String, Object> templateParams) {
        String pipelineTemplate = getPipelineTemplate(pipelineName, stage);
        TemplateEngine engine = TemplateUtil.createEngine();
        Template template = engine.getTemplate(pipelineTemplate);
        return template.render(templateParams);
    }


    public String getPipelineParams(String pipelineTemplate, Map<String, Object> templateParams) {
        TemplateEngine engine = TemplateUtil.createEngine();
        Template template = engine.getTemplate(pipelineTemplate);
        return template.render(templateParams);
    }

    public String getPipelineTemplate(String pipelineName, StageEnum stage) {
        ConfigQry configQry = new ConfigQry();
        configQry.setConfigKey(StrUtil.format("{}_{}_template", pipelineName, stage));
        configQry.setSystemCode(CommonConst.SYSTEM_CODE);
        Result<ConfigItemDTO> detail = configQryFacade.detail(configQry);
        ConfigItemDTO configItemDTO = detail.getData();
        return configItemDTO.getConfigValue();
    }
}
