package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

/**
 * schema实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class SchemaDTO {
    private String tableName;
    private String tableCname;
    private List<PropertyDesc> propertyList;
}
