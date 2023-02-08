package com.digitforce.aip.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.digitforce.aip.config.AutoMLProperties;
import com.digitforce.aip.consts.SolutionErrorCode;
import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;
import com.digitforce.aip.service.AutoMLService;
import com.digitforce.framework.api.exception.BizException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class AutoMLServiceImpl implements AutoMLService {
    @Resource
    private AutoMLProperties autoMLProperties;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    @SneakyThrows
    public String createTask(String templateParams) {
        log.info("创建自动机器学习任务，请求参数：{}", templateParams);
        HttpRequest request = HttpRequest.post(StrUtil.format("{}/create-task", autoMLProperties.getBaseUrl()))
                .header(Header.CONTENT_TYPE, "application/json")
                .body(templateParams);
        String body = request.execute().body();
        log.info("创建自动机器学习任务，返回结果：{}", body);
        try {
            Map<String, Object> res = objectMapper.readValue(body, new TypeReference<Map<String, Object>>() {
            });
            return res.get("run_id").toString();
        } catch (Exception e) {
            log.error("创建自动机器学习任务失败", e);
            throw BizException.of(SolutionErrorCode.AUTO_ML_CREATE_TASK_ERROR);
        }
    }

    @Override
    @SneakyThrows
    public AutoMLRunStatusEnum getStatus(String runId) {
        String url = StrUtil.format("{}/get-experiment-status?run_id={}",
                autoMLProperties.getBaseUrl(), runId);
        HttpResponse httpResponse = HttpRequest.get(url).execute();
        if (httpResponse.getStatus() != 200) {
            throw BizException.of(SolutionErrorCode.AUTO_ML_GET_STATUS_ERROR);
        }
        Map<String, Object> result =
                objectMapper.readValue(httpResponse.body(), new TypeReference<Map<String, Object>>() {
                });
        log.info("getStatus result: {}", result);
        Integer code = (Integer) result.get("code");
        if (code == null) {
            throw BizException.of(SolutionErrorCode.AUTO_ML_GET_STATUS_ERROR);
        }
        return AutoMLRunStatusEnum.getEnum(code);
    }

    @Override
    @SneakyThrows
    public List<BestParameter> getAutoMLResult(String runId) {
        String url = StrUtil.format("{}/get-parameters?run_id={}",
                autoMLProperties.getBaseUrl(), runId);
        HttpResponse httpResponse = HttpRequest.get(url).execute();
        if (httpResponse.getStatus() != 200) {
            throw new RuntimeException("获取自动机器学习运行结果失败");
        }
        Map<String, Object> result =
                objectMapper.readValue(httpResponse.body(), new TypeReference<Map<String, Object>>() {
                });
        String data = objectMapper.writeValueAsString(result.get("data"));
        return objectMapper.readValue(data, new TypeReference<List<BestParameter>>() {
        });
    }
}
