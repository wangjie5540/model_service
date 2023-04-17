package com.digitforce.aip.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.digitforce.aip.entity.PredictDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@InterceptorIgnore(tenantLine = "true")
@DS("olap")
public interface PredictResultMapper {
    @Select("WITH total_users AS (" +
            "    SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}" +
            ")," +
            "top_n_users AS (" +
            "    SELECT user_id, score," +
            "           ROW_NUMBER() OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}" +
            ")," +
            "top_n_stats AS (" +
            "    SELECT COUNT(*) AS top_n_count," +
            "           MIN(score) AS min_score," +
            "           MAX(score) AS max_score" +
            "    FROM top_n_users" +
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
    Map<String, Object> all(@Param("tableName") String tableName, @Param("instanceId") Long instanceId);

    @Select("WITH total_users AS (" +
            "    SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}" +
            ")," +
            "top_n_users AS (" +
            "    SELECT user_id, score," +
            "           ROW_NUMBER() OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}" +
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
    Map<String, Object> topN(@Param("tableName") String tableName, @Param("instanceId") Long instanceId,
                             @Param("n") Long n);

    @Select("WITH total_users AS " +
            "    (SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName}  WHERE instance_id = #{instanceId}), " +
            "    " +
            "    top_n_users AS " +
            "    (SELECT user_id," +
            "            score," +
            "            ROW_NUMBER()" +
            "             OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}), " +
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
    Map<String, Object> topPercent(@Param("tableName") String tableName, @Param("instanceId") Long instanceId,
                                   @Param("percent") Double percent);

    @Select("WITH total_users AS (" +
            "    SELECT COUNT(*) AS total_count" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}" +
            ")," +
            "top_n_users AS (" +
            "    SELECT user_id, score," +
            "           ROW_NUMBER() OVER (ORDER BY score DESC) AS ranking" +
            "    FROM ${tableName} WHERE instance_id = #{instanceId}" +
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
    Map<String, Object> targetScore(@Param("tableName") String tableName, @Param("instanceId") Long instanceId,
                                    @Param("start") Double start, @Param("end") Double end);


    @Select("WITH score_ranges AS ( " +
            "    SELECT '0 - 0.1' AS range_label, 0 AS range_start, 0.1 AS range_end " +
            "    UNION ALL " +
            "    SELECT '0.1 - 0.2', 0.1, 0.2 " +
            "    UNION ALL " +
            "    SELECT '0.2 - 0.3', 0.2, 0.3 " +
            "    UNION ALL " +
            "    SELECT '0.3 - 0.4', 0.3, 0.4 " +
            "    UNION ALL " +
            "    SELECT '0.4 - 0.5', 0.4, 0.5 " +
            "    UNION ALL " +
            "    SELECT '0.5 - 0.6', 0.5, 0.6 " +
            "    UNION ALL " +
            "    SELECT '0.6 - 0.7', 0.6, 0.7 " +
            "    UNION ALL " +
            "    SELECT '0.7 - 0.8', 0.7, 0.8 " +
            "    UNION ALL " +
            "    SELECT '0.8 - 0.9', 0.8, 0.9 " +
            "    UNION ALL " +
            "    SELECT '0.9 - 1', 0.9, 1 " +
            "), " +
            "user_counts AS ( " +
            "    SELECT " +
            "        COUNT(*) AS user_count, " +
            "        CASE " +
            "            WHEN score >= 0 AND score < 0.1 THEN '0 - 0.1' " +
            "            WHEN score >= 0.1 AND score < 0.2 THEN '0.1 - 0.2' " +
            "            WHEN score >= 0.2 AND score < 0.3 THEN '0.2 - 0.3' " +
            "            WHEN score >= 0.3 AND score < 0.4 THEN '0.3 - 0.4' " +
            "            WHEN score >= 0.4 AND score < 0.5 THEN '0.4 - 0.5' " +
            "            WHEN score >= 0.5 AND score < 0.6 THEN '0.5 - 0.6' " +
            "            WHEN score >= 0.6 AND score < 0.7 THEN '0.6 - 0.7' " +
            "            WHEN score >= 0.7 AND score < 0.8 THEN '0.7 - 0.8' " +
            "            WHEN score >= 0.8 AND score < 0.9 THEN '0.8 - 0.9' " +
            "            WHEN score >= 0.9 AND score <= 1 THEN '0.9 - 1' " +
            "        END AS score_range " +
            "    FROM " +
            "        ${tableName} WHERE instance_id = #{instanceId}" +
            "    GROUP BY " +
            "        score_range " +
            ") " +
            "SELECT " +
            "    sr.range_label AS score_range, " +
            "    COALESCE(uc.user_count, 0) AS `count` " +
            "FROM " +
            "    score_ranges sr " +
            "LEFT JOIN " +
            "    user_counts uc " +
            "ON " +
            "    sr.range_label = uc.score_range " +
            "ORDER BY " +
            "    sr.range_start " +
            "DESC; ")
    List<Map<String, Object>> getBaseRange(@Param("tableName") String tableName,
                                           @Param("instanceId") Long instanceId);

    @Select("WITH score_ranges AS ( " +
            "  SELECT " +
            "    FLOOR(score * 10) * 0.1 AS lower_range, " +
            "    FLOOR(score * 10) * 0.1 + 0.1 AS upper_range " +
            "  FROM ${tableName} s" +
            "  WHERE score BETWEEN #{start} AND #{end} AND instance_id = #{instanceId}" +
            ") " +
            "SELECT " +
            "  CONCAT(CAST(lower_range AS DECIMAL(2, 1)), ' - ', CAST(upper_range AS DECIMAL(2, 1))) AS " +
            "score_range," +
            " " +
            "  COUNT(*) AS `count` " +
            "FROM score_ranges " +
            "GROUP BY lower_range, upper_range " +
            "ORDER BY lower_range, upper_range;")
    List<Map<String, Object>> getTargetScoreDistribution(@Param("tableName") String tableName,
                                                         @Param("instanceId") Long instanceId,
                                                         @Param("start") Double start,
                                                         @Param("end") Double end);

    @Select("select user_id, score from ${tableName} where score between #{start} and #{end} and instance_id = " +
            "#{instanceId} order by score desc limit #{limit}")
    List<Map<String, Object>> getPredictDetail(@Param("tableName") String tableName,
                                               @Param("instanceId") Long instanceId,
                                               @Param("start") Double start,
                                               @Param("end") Double end,
                                               @Param("limit") Integer limit);

    @Select("select instance_id, user_id, score from ${tableName} where " +
            "instance_id = #{instanceId} and " +
            "score between #{start} and #{end} " +
            "order by score desc limit #{limit}")
    List<PredictDetail> getPredictDetailList(@Param("tableName") String tableName,
                                             @Param("instanceId") Long instanceId,
                                             @Param("start") Double start,
                                             @Param("end") Double end,
                                             @Param("limit") Long limit);

    @Select("select `shapley` from ${tableName} where instance_id = #{instanceId} and user_id = #{userId}")
    String getShapley(@Param("tableName") String tableName, @Param("instanceId") Long instanceId,
                      @Param("userId") Long userId);
}
