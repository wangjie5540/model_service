package com.digitforce.aip.service.component;

import com.digitforce.aip.entity.UserScore;
import com.digitforce.aip.mapper.PredictResultMapper;
import com.digitforce.aip.utils.OlapHelper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Service
public class DownloadService {
    @Resource
    private PredictResultMapper predictResultMapper;

    @SneakyThrows
    public void downloadResult(OutputStream outputStream, Long solutionId, Long instanceId, Double start, Double end,
                               Long total) {
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
            // Write users data
            String tableName = OlapHelper.getScoreTableName(solutionId);
            try (Stream<UserScore> userStream = predictResultMapper
                    .getPredictDetailList(tableName, instanceId, start, end, total)) {
                userStream.forEach(userScore -> {
                    try {
                        writer.write(userScore.getUserId() + "," + userScore.getScore());
                        writer.newLine();
                    } catch (IOException e) {
                        throw new UncheckedIOException(e);
                    }
                });
            } catch (UncheckedIOException e) {
                throw e.getCause();
            }
        }
    }
}
