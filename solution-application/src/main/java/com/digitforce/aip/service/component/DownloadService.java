package com.digitforce.aip.service.component;

import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.digitforce.aip.utils.OlapHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class DownloadService {
    @Resource
    private DataSource dataSource;

    @SneakyThrows
    public void downloadResult(OutputStream outputStream, Long solutionId, Long instanceId, Double start, Double end,
                               Long total) {
        DynamicRoutingDataSource dynamicRoutingDataSource = (DynamicRoutingDataSource) dataSource;
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dynamicRoutingDataSource.getDataSource("olap"));
        String sql = StrUtil.format("select user_id, score from {} where instance_id = {} and score between {} and {}" +
                        " order by score desc limit {}",
                OlapHelper.getScoreTableName(solutionId),
                instanceId.toString(),
                start.toString(),
                end.toString(),
                total.toString()
        );
        log.info("query sql: {}", sql);
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            // Write users data
            jdbcTemplate.query(sql, resultSet -> {
                try {
                    String userId = resultSet.getString("user_id");
                    double score = resultSet.getDouble("score");

                    writer.write(userId + "," + score);
                    writer.newLine();
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        }
    }
}
