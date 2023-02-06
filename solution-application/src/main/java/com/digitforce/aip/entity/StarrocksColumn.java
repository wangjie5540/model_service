package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * starrocks columns
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
@Data
@TableName("columns")
public class StarrocksColumn implements Serializable {
    private static final long serialVersionUID = 4624460191951600389L;
    @TableField(value = "TABLE_SCHEMA")
    private String tableSchema;
    @TableField(value = "TABLE_NAME")
    private String tableName;
    @TableField(value = "COLUMN_NAME")
    private String columnName;
    @TableField(value = "DATA_TYPE")
    private String dataType;
    @TableField(value = "COLUMN_TYPE")
    private String columnType;
}
