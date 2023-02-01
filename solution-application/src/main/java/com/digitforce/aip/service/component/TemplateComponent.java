package com.digitforce.aip.service.component;

import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.consts.SolutionErrorCode;
import com.digitforce.aip.enums.StageEnum;
import com.digitforce.component.config.api.dto.data.ConfigItemDTO;
import com.digitforce.component.config.api.dto.qry.ConfigQry;
import com.digitforce.component.config.api.facade.qry.ConfigQryFacade;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.api.exception.BizException;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.StringWriter;
import java.util.Map;

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
        return getPipelineParams(pipelineTemplate, templateParams);
    }


    /**
     * 渲染pipeline参数
     *
     * @param pipelineTemplate 模板
     * @param templateParams   模板参数
     * @return pipeline参数
     */
    public String getPipelineParams(String pipelineTemplate, Map<String, Object> templateParams) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_30);
        configuration.setNumberFormat("0.#####");
        try {
            Template template = new Template("template", pipelineTemplate,
                    configuration);
            StringWriter writer = new StringWriter();
            template.process(templateParams, writer);
            return writer.toString();
        } catch (Exception e) {
            throw BizException.of(SolutionErrorCode.TEMPLATE_PARAMS_ERROR);
        }
    }

    public String getPipelineTemplate(String pipelineName, StageEnum stage) {
        ConfigQry configQry = new ConfigQry();
        configQry.setConfigKey(StrUtil.format("{}_{}_template", pipelineName, stage));
        configQry.setSystemCode(CommonConst.SYSTEM_CODE);
        Result<ConfigItemDTO> detail = configQryFacade.detail(configQry);
        ConfigItemDTO configItemDTO = detail.getData();
        if (configItemDTO == null) {
            throw BizException.of(SolutionErrorCode.TEMPLATE_NOT_EXIST);
        }
        return configItemDTO.getConfigValue();
    }
}
