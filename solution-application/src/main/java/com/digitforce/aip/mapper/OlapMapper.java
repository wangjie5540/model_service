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

    @Select("WITH total_users AS (" +
            "    SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName}" +
            ")," +
            "top_n_users AS (" +
            "    SELECT user_id, score," +
            "           ROW_NUMBER() OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName}" +
            ")," +
            "top_n_stats AS (" +
            "    SELECT COUNT(*) AS top_n_count," +
            "           MIN(score) AS min_score," +
            "           MAX(score) AS max_score" +
            "    FROM top_n_users" +
            "    WHERE ranking <= #{n}" +
            ")," +
            "top_n_percent AS (" +
            "    SELECT (top_n_stats.top_n_count / total_users.total_count) AS top_n_percentage" +
            "    FROM top_n_stats, total_users" +
            ")" +
            "SELECT top_n_stats.top_n_count AS `total`," +
            "       top_n_percent.top_n_percentage AS `ratio`," +
            "       top_n_stats.min_score," +
            "       top_n_stats.max_score" +
            " FROM top_n_stats, top_n_percent")
    Map<String, Object> topN(@Param("tableName") String tableName, @Param("n") Long n);

    @Select("WITH total_users AS " +
            "    (SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName} ), " +
            "    " +
            "    top_n_users AS " +
            "    (SELECT user_id," +
            "            score," +
            "            ROW_NUMBER()" +
            "             OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName} ), " +
            "    " +
            "    top_n_users_joined AS" +
            "    (SELECT tnu.user_id," +
            "            tnu.score," +
            "            tnu.ranking," +
            "            tu.total_count" +
            "     FROM top_n_users tnu" +
            "     CROSS JOIN total_users tu)," +
            "" +
            "    top_n_stats AS " +
            "    (SELECT COUNT(*) AS top_n_count," +
            "            MIN(score) AS min_score," +
            "            MAX(score) AS max_score" +
            "    FROM top_n_users_joined" +
            "    WHERE ranking <= total_count * #{percent} ), " +
            "    " +
            "    top_n_percent AS " +
            "    (SELECT (top_n_stats.top_n_count * #{percent} / total_users.total_count) AS top_n_percentage" +
            "    FROM top_n_stats, total_users )" +
            "SELECT top_n_stats.top_n_count AS total," +
            "       top_n_percent.top_n_percentage AS ratio," +
            "       top_n_stats.min_score," +
            "       top_n_stats.max_score" +
            " FROM top_n_stats, top_n_percent;")
    Map<String, Object> topPercent(@Param("tableName") String tableName, @Param("percent") Double percent);

    @Select("WITH total_users AS (" +
            "    SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName}" +
            ")," +
            "top_n_users AS (" +
            "    SELECT user_id, score," +
            "           ROW_NUMBER() OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName}" +
            ")," +
            "top_n_stats AS (" +
            "    SELECT COUNT(*) AS top_n_count," +
            "           MIN(score) AS min_score," +
            "           MAX(score) AS max_score" +
            "    FROM top_n_users" +
            "    WHERE score between #{start} and #{end}" +
            ")," +
            "top_n_percent AS (" +
            "    SELECT (top_n_stats.top_n_count / total_users.total_count) AS top_n_percentage" +
            "    FROM top_n_stats, total_users" +
            ")" +
            "SELECT top_n_stats.top_n_count AS total," +
            "       top_n_percent.top_n_percentage AS ratio," +
            "       top_n_stats.min_score," +
            "       top_n_stats.max_score" +
            " FROM top_n_stats, top_n_percent;")
    Map<String, Object> scoreRange(@Param("tableName") String tableName, @Param("start") Double start,
                                   @Param("end") Double end);
}
