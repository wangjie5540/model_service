package com.digitforce.aip.mapper;

import cn.hutool.core.util.StrUtil;
import org.apache.ibatis.builder.annotation.ProviderMethodResolver;

import java.util.Map;

public class StarrocksSqlProvider implements ProviderMethodResolver {

    public String scoreTable(Map<String, Object> params) {
        String tableName = (String) params.get("tableName");
        String createTableTemplate = "" +
                "create table if not exists {} (\n" +
                "    instance_id bigint NOT NULL,\n" +
                "    user_id varchar(256) NOT NULL,\n" +
                "    score FLOAT NOT NULL\n" +
                ") PRIMARY KEY (instance_id, user_id)\n" +
                "PARTITION BY RANGE (instance_id) ()\n" +
                "DISTRIBUTED BY HASH(user_id) BUCKETS 4\n" +
                "PROPERTIES(\"replication_num\" = \"1\");";
        return StrUtil.format(createTableTemplate, tableName);
    }
}