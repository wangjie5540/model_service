package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.PropertyDesc;
import com.digitforce.aip.dto.data.SchemaDTO;
import com.digitforce.aip.dto.qry.SchemaGetByTableQry;
import com.digitforce.aip.dto.qry.SchemaListTableQry;
import com.digitforce.component.dict.api.client.DictEntryQryFacade;
import com.digitforce.component.dict.api.dto.DictEntryDTO;
import com.digitforce.framework.api.dto.Result;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * schema查询实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class SchemaQryFacadeImpl implements SchemaQryFacade {
    private static final Map<String, SchemaDTO> schemaMap = new HashMap<>();

    static {
        SchemaDTO schemaDTO = new SchemaDTO();
        schemaDTO.setTableName("goods_table");
        List<PropertyDesc> propertyDescList = Lists.newArrayList();
        propertyDescList.add(new PropertyDesc("category", "品类", "string"));
        propertyDescList.add(new PropertyDesc("tags", "物品标签", "string"));
        propertyDescList.add(new PropertyDesc("publish_time", "发布时间", "datetime"));
        schemaDTO.setPropertyList(propertyDescList);
        schemaMap.put("goods_table", schemaDTO);
        schemaDTO = new SchemaDTO();
        schemaDTO.setTableName("user_table");
        propertyDescList = Lists.newArrayList();
        propertyDescList.add(new PropertyDesc("age", "品类", "numeric"));
        propertyDescList.add(new PropertyDesc("last_login_time", "上次登录时间", "datetime"));
        schemaDTO.setPropertyList(propertyDescList);
        schemaMap.put("user_table", schemaDTO);
    }

    @Resource
    private DictEntryQryFacade dictEntryQryFacade;

    @Override
    public Result<List<String>> listTable(SchemaListTableQry schemaListTableQry) {
        DictEntryDTO dictEntryDTO = new DictEntryDTO();
        dictEntryDTO.setTypeKey("screen_table");
        List<DictEntryDTO> dictEntryDTOList = dictEntryQryFacade.listByTypeKey(dictEntryDTO).getData();
        return Result.success(dictEntryDTOList.stream().map(DictEntryDTO::getEntryKey).collect(Collectors.toList()));
    }

    @Override
    public Result<SchemaDTO> getByTable(SchemaGetByTableQry schemaGetByTableQry) {
        return Result.success(schemaMap.get(schemaGetByTableQry.getName()));
    }
}
