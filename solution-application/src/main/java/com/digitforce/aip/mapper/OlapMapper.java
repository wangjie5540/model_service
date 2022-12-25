package com.digitforce.aip.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.digitforce.aip.entity.StarrocksColumn;
import com.digitforce.aip.entity.StarrocksTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 查询starrocks使用的mapper
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/25 12:16
 */
@Mapper
@InterceptorIgnore(tenantLine = "true")
@DS("olap")
public interface OlapMapper {
    @Select("select TABLE_SCHEMA, TABLE_NAME from information_schema.tables where TABLE_SCHEMA = #{tableSchema} and TABLE_NAME = #{tableName}")
    StarrocksTable getTable(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName);

    @Select("select TABLE_SCHEMA, TABLE_NAME, COLUMN_NAME, DATA_TYPE, COLUMN_TYPE from information_schema.columns where TABLE_SCHEMA = #{tableSchema} and TABLE_NAME = #{tableName} and COLUMN_NAME = #{columnName}")
    StarrocksColumn getColumn(@Param("tableSchema") String tableSchema, @Param("tableName") String tableName,
        @Param("columnName") String columnName);
}
