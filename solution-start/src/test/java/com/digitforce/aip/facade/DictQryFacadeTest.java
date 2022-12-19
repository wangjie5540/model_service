package com.digitforce.aip.facade;

import cn.hutool.json.JSONUtil;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.component.dict.dto.data.DictTreeDTO;
import com.digitforce.component.dict.dto.qry.DictTreeQry;
import com.digitforce.component.dict.facade.qry.DictDataQryFacade;
import com.digitforce.component.dict.facade.qry.DictQryFacade;
import com.digitforce.framework.api.dto.Result;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字典服务查询接口测试
 * 参考：<a href="http://dev-new.digitforce.com/idp-dict/#/">字典服务GUI</a>
 *
 * @author wangtonggui
 */
public class DictQryFacadeTest extends BaseTest {
    @Resource
    private DictQryFacade dictQryFacade;
    @Resource
    private DictDataQryFacade dictDataQryFacade;

    @Test
    public void dictTree() {
        DictTreeQry dictTreeQry = new DictTreeQry();
        dictTreeQry.setSystemCode("算法策略平台");
        dictTreeQry.setNodeKey("test_dict");
        Result<List<DictTreeDTO>> listResult = dictQryFacade.queryDictTree(dictTreeQry);
        List<DictTreeDTO> data = listResult.getData();
        System.out.println(JSONUtil.toJsonPrettyStr(data));
    }
}
