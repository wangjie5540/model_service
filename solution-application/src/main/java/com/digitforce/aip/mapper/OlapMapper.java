package com.digitforce.aip.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@InterceptorIgnore(tenantLine = "true")
@DS("olap")
public interface OlapMapper {
    @Select("select ${columns} from ${tableName} limit 1")
    Map<String, Object> selectOne(@Param("tableName") String tableName, @Param("columns") String columns);
}
