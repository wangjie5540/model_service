package com.digitforce.aip.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;

@InterceptorIgnore(tenantLine = "true")
@DS("olap")
public interface StarrocksDDLMapper {
    @UpdateProvider(type = StarrocksSqlProvider.class, method = "scoreTable")
    void createScoreTable(@Param("tableName") String tableName);

    @Update("ALTER TABLE ${tableName} ADD PARTITIONS START (\"${start}\") END (\"${end}\") EVERY (1)")
    void createPartition(@Param("tableName") String tableName, @Param("start") Long start, @Param("end") Long end);

    @Update("drop table if exists ${tableName}")
    void dropTable(@Param("tableName") String tableName);
}
