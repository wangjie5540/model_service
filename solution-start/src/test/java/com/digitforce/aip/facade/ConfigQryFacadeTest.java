package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.component.config.api.dto.data.ConfigItemDTO;
import com.digitforce.component.config.api.dto.qry.ConfigQry;
import com.digitforce.component.config.api.facade.qry.ConfigQryFacade;
import com.digitforce.framework.api.dto.Result;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

import javax.annotation.Resource;

/**
 * 配置服务查询接口测试
 * 参考：<a href="http://dev-new.digitforce.com/idp-components/#/settingConfig">配置服务GUI</a>
 */
public class ConfigQryFacadeTest extends BaseTest {
    @Resource
    private ConfigQryFacade configQryFacade;

    @Test
    public void detailTest() {
        ConfigQry configQry = new ConfigQry();
        configQry.setSystemCode(CommonConst.SYSTEM_CODE);
        configQry.setConfigKey("lookalike");
        Result<ConfigItemDTO> detail = configQryFacade.detail(configQry);
        ConfigItemDTO data = detail.getData();
        String configValue = data.getConfigValue();
        Yaml yaml = new Yaml();
        Map<String, Object> load = yaml.load(configValue);
        Object dynamicForm = load.get("dynamicForm");
        System.out.println(dynamicForm);
    }
}
