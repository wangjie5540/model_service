package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * starrocks tables
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
@Data
@TableName("tables")
public class StarrocksTable implements Serializable {
    private static final long serialVersionUID = 1736544758691998362L;
    @TableField(value = "TABLE_SCHEMA")
    private String tableSchema;
    @TableField(value = "TABLE_NAME")
    private String tableName;
}
