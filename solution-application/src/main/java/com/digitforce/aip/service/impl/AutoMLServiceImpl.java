package com.digitforce.aip.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.digitforce.aip.config.AutoMLProperties;
import com.digitforce.aip.entity.dto.cmd.AutomlParams;
import com.digitforce.aip.entity.dto.data.BestParameter;
import com.digitforce.aip.enums.AutoMLRunStatusEnum;
import com.digitforce.aip.service.AutoMLService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

@Service
public class AutoMLServiceImpl implements AutoMLService {
    @Resource
    private AutoMLProperties autoMLProperties;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    public String createTask(AutomlParams automlParams) {
        HttpRequest.get("http://localhost:8080/api/v1/namespaces/kubeflow/pipelines");
        return null;
    }

    @Override
    @SneakyThrows
    public AutoMLRunStatusEnum getStatus(String runId) {
        String url = StrUtil.format("{}/aip/v1/automl/get-experiment-status?run_id={}",
            autoMLProperties.getBaseUrl(), runId);
        HttpResponse httpResponse = HttpRequest.get(url).execute();
        if (httpResponse.getStatus() != 200) {
            throw new RuntimeException("获取自动机器学习运行状态失败");
        }
        Map<String, Object> result =
            objectMapper.readValue(httpResponse.body(), new TypeReference<Map<String, Object>>() {
            });
        return AutoMLRunStatusEnum.getEnum((Integer) result.get("status"));
    }

    @Override
    @SneakyThrows
    public List<BestParameter> getAutoMLResult(String runId) {
        String url = StrUtil.format("{}/aip/v1/automl/get-parameters?run_id={}",
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
