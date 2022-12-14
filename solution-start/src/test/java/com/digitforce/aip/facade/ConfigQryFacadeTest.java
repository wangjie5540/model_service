package com.digitforce.aip.facade;

import cn.hutool.json.JSONUtil;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.component.config.api.dto.data.ConfigItemDTO;
import com.digitforce.component.config.api.dto.qry.ConfigQry;
import com.digitforce.component.config.api.facade.qry.ConfigQryFacade;
import com.digitforce.framework.api.dto.Result;
import org.junit.Test;

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
        configQry.setSystemCode("public");
        configQry.setConfigKey("WWWW");
        Result<ConfigItemDTO> detail = configQryFacade.detail(configQry);
        ConfigItemDTO data = detail.getData();
        System.out.println(JSONUtil.toJsonPrettyStr(data));
    }
}
