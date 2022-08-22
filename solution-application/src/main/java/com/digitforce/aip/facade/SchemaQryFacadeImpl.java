package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.PropertyDesc;
import com.digitforce.aip.dto.data.SchemaDTO;
import com.digitforce.aip.dto.qry.SchemaGetByTableQry;
import com.digitforce.aip.dto.qry.SchemaListTableQry;
import com.digitforce.aip.enums.DataTypeEnum;
import com.digitforce.component.dict.api.dto.DictEntryDTO;
import com.digitforce.framework.api.dto.Result;
import com.google.common.collect.Lists;
import org.springframework.web.bind.annotation.RestController;

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
    // TODO mock
    public static final List<PropertyDesc> contentPropertyList = Lists.newArrayList();
    public static final List<PropertyDesc> userPropertyList = Lists.newArrayList();
    public static final List<PropertyDesc> trafficPropertyList = Lists.newArrayList();


    static {
        SchemaDTO schemaDTO = new SchemaDTO();
        schemaDTO.setTableName("goods_table");
        schemaDTO.setTableCname("内容表");
        contentPropertyList.add(new PropertyDesc("title", "标题", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("content", "内容", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("keyword", "关键词", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("tag", "标签", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("ins_tag", "行业标签", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("cap_tag", "概念标签", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("stock_list", "提及个股", DataTypeEnum.ARRAY));
        contentPropertyList.add(new PropertyDesc("content_len", "全文长度", DataTypeEnum.NUMERIC));
        contentPropertyList.add(new PropertyDesc("has_pic", "是否有配图", DataTypeEnum.BOOL));
        contentPropertyList.add(new PropertyDesc("has_cover", "是否有封面", DataTypeEnum.BOOL));
        contentPropertyList.add(new PropertyDesc("create_time", "创建时间", DataTypeEnum.DATETIME));
        contentPropertyList.add(new PropertyDesc("author_id", "作者ID", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("content_style", "作者内容风格", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("inv_style", "作者投资风格", DataTypeEnum.STRING));
        contentPropertyList.add(new PropertyDesc("fans_cnt", "作者粉丝数", DataTypeEnum.NUMERIC));
        contentPropertyList.add(new PropertyDesc("eval_read_time", "预估阅读时长", DataTypeEnum.NUMERIC));
        schemaDTO.setPropertyList(contentPropertyList);
        schemaMap.put("内容表", schemaDTO);
        schemaDTO = new SchemaDTO();
        schemaDTO.setTableName("user_table");
        schemaDTO.setTableCname("用户表");
        userPropertyList.add(new PropertyDesc("user_id", "用户ID", DataTypeEnum.STRING));
        userPropertyList.add(new PropertyDesc("sex", "性别", DataTypeEnum.STRING));
        userPropertyList.add(new PropertyDesc("age", "年龄", DataTypeEnum.NUMERIC));
        userPropertyList.add(new PropertyDesc("city", "城市", DataTypeEnum.STRING));
        userPropertyList.add(new PropertyDesc("education", "教育", DataTypeEnum.STRING));
        userPropertyList.add(new PropertyDesc("is_hs", "是否开通A股", DataTypeEnum.BOOL));
        userPropertyList.add(new PropertyDesc("is_yb", "是否开通创业板", DataTypeEnum.BOOL));
        userPropertyList.add(new PropertyDesc("is_kc", "是否开通科创板", DataTypeEnum.BOOL));
        userPropertyList.add(new PropertyDesc("rank", "风险等级", DataTypeEnum.STRING));
        schemaDTO.setPropertyList(userPropertyList);
        schemaMap.put("用户表", schemaDTO);


        schemaDTO = new SchemaDTO();
        schemaDTO.setTableName("traffic_table");
        schemaDTO.setTableCname("流量表");
        trafficPropertyList.add(new PropertyDesc("user_id", "用户ID", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("content_id", "内容ID", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("fund_code", "基金ID", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("from_page", "来源页面", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("event_type", "事件类型", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("event_timestamp", "时间戳", DataTypeEnum.DATETIME));
        trafficPropertyList.add(new PropertyDesc("session_id", "session_ID", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("page_uuid", "页面ID", DataTypeEnum.STRING));
        trafficPropertyList.add(new PropertyDesc("dur_time", "停留时长", DataTypeEnum.NUMERIC));
        trafficPropertyList.add(new PropertyDesc("input_content", "输入内容", DataTypeEnum.STRING));
        schemaDTO.setPropertyList(trafficPropertyList);
        schemaMap.put("流量表", schemaDTO);
    }

//    @Resource
//    private DictEntryQryFacade dictEntryQryFacade;

    @Override
    public Result<List<String>> listTable(SchemaListTableQry schemaListTableQry) {
        DictEntryDTO dictEntryDTO = new DictEntryDTO();
        dictEntryDTO.setTypeKey("screen_table");
//        List<DictEntryDTO> dictEntryDTOList = dictEntryQryFacade.listByTypeKey(dictEntryDTO).getData();
        List<DictEntryDTO> dictEntryDTOS = Lists.newArrayList();
        DictEntryDTO dto = new DictEntryDTO();
        dto.setEntryName("内容表");
        dictEntryDTOS.add(dto);
        return Result.success(dictEntryDTOS.stream().map(DictEntryDTO::getEntryName).collect(Collectors.toList()));
    }

    @Override
    public Result<SchemaDTO> getByTable(SchemaGetByTableQry schemaGetByTableQry) {
        return Result.success(schemaMap.get(schemaGetByTableQry.getName()));
    }
}
