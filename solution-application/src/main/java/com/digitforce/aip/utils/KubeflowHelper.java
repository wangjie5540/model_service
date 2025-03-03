package com.digitforce.aip.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.digitforce.aip.dto.data.Pipeline;
import com.digitforce.aip.entity.PipelinePage;
import com.digitforce.framework.util.GsonUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对接kubeflow的rest-api
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 17:04
 */
@UtilityClass
@Slf4j
public class KubeflowHelper {
    private static final Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();
    // https://digit-force.coding.net/p/ai-platform/km/spaces/73/pages/K-259
    // TODO 后续将优化kubeflow的账户系统，此处先写死
    public static final String KUBEFLOW_LOGIN_USER = "admin@example.com";
    public static final String KUBEFLOW_LOGIN_PASSWORD = "password";

    /**
     * kubeflow登录
     *
     * @param host kubeflow服务器的host
     * @param port kubeflow服务器的port
     */
    public void login(String host, int port) {
        String rep = HttpUtil.get(String.format("http://%s:%d", host, port));
        String query = getQuery(rep);
        if (query == null) {
            // already login
            return;
        }
        String state = getQueryParam(query, "state");
        rep = HttpUtil.get(String.format(
                "http://%s:%d/dex/auth?client_id=kubeflow-oidc-authservice&redirect_uri=/login/oidc&response_type" +
                        "=code&scope=profile+email+groups+openid&amp;state=%s",
                host, port, state));
        query = getQuery(rep);
        Map<String, Object> loginData = new HashMap<>();
        loginData.put("login", KUBEFLOW_LOGIN_USER);
        loginData.put("password", KUBEFLOW_LOGIN_PASSWORD);
        String req = getQueryParam(query, "req");
        HttpRequest request = HttpRequest.post(
                        String.format("http://%s:%d/dex/auth/local?req=%s", host, port, req))
                .header(Header.CONTENT_TYPE, "application/x-www-form-urlencoded")
                .form(loginData);
        request.execute();
        rep = HttpUtil.get(String.format("http://%s:%d/dex/approval?req=%s", host, port, req));
        query = getQuery(rep);
        String code = getQueryParam(query, "code");
        HttpRequest.get(
                String.format("http://%s:%d/login/oidc?code=%s&state=%s", host, port, code, state)).execute();
    }

    public Pipeline getPipelineDetail(String host, int port, String pipelineId) {
        login(host, port);
        String body = HttpRequest.get(String.format(
                "http://%s:%d/pipeline/apis/v1beta1/pipelines/%s", host, port, pipelineId)).execute().body();
        return gson.fromJson(body, Pipeline.class);
    }

    private String getQueryParam(String query, String key) {
        Map<String, String> map = new HashMap<>();
        for (String item : query.split("&")) {
            String[] split = item.split("=");
            map.put(split[0], split[1]);
        }
        return map.get(key);
    }

    private String getQuery(String html) {
        Document parse = Jsoup.parse(html);
        Elements a = parse.select("a");
        return URI.create(a.attr("href")).getQuery();
    }

    public String createRun(
            String host,
            int port,
            String experimentId,
            String pipelineId,
            String runName,
            String globalParams,
            String flag
    ) {
        login(host, port);
        List<Map<String, Object>> pipelineParameters = new ArrayList<>();
        Map<String, Object> parameter = new HashMap<>();
        // 所有的pipeline都需要传递json字符串形式的global_params参数
        parameter.put("name", "global_params");
        parameter.put("value", globalParams);
        pipelineParameters.add(parameter);
        parameter = new HashMap<>();
        parameter.put("name", "flag");
        parameter.put("value", flag);
        pipelineParameters.add(parameter);
        HttpRequest httpRequest = HttpRequest.post(String.format("http://%s:%d/pipeline/apis/v1beta1/runs", host, port))
                .body(generateBody(runName, experimentId, pipelineId, pipelineParameters));
        String body = httpRequest.execute().body();
        log.info("kubeflow response. [body={}]", body);
        RunDetail runDetail = GsonUtil.gsonToBean(body, RunDetail.class);
        return runDetail.run.getId();
    }

    private String getStartDateStr(Integer offset, ChronoUnit chronoUnit) {
        LocalDateTime localDateTime = LocalDateTimeUtil.offset(LocalDateTime.now(), -1L * offset, chronoUnit);
        localDateTime = LocalDateTimeUtil.beginOfDay(localDateTime);
        return DateUtil.format(localDateTime, "yyyy-MM-dd");
    }

    public void stopRun(String host, int port, String runId) {
        login(host, port);
        HttpRequest httpRequest = HttpRequest.post(String.format("http://%s:%d/pipeline/apis/v1beta1/runs/%s" +
                "/terminate", host, port, runId));
        HttpResponse execute = httpRequest.execute();
        if (execute.getStatus() != 200) {
            throw new RuntimeException(String.format("stopRun error. [host=%s,port=%d,runId=%s]", host, port, runId));
        }
    }

    private String generateBody(String name, String experimentId, String pipelineId,
                                List<Map<String, Object>> pipelineParameters) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("name", name);
        paramsMap.put("description", "");
        Map<String, Object> pipelineSpecMap = new HashMap<>();
        pipelineSpecMap.put("pipeline_id", pipelineId);
        pipelineSpecMap.put("parameters", pipelineParameters);
        paramsMap.put("pipeline_spec", pipelineSpecMap);
        List<Reference> referenceList = new ArrayList<>();
        Reference experimentRef = new Reference();
        experimentRef.relationship = "OWNER";
        experimentRef.key.id = experimentId;
        experimentRef.key.type = "EXPERIMENT";
        referenceList.add(experimentRef);
        paramsMap.put("resource_references", referenceList);
        paramsMap.put("service_account", "");
        return GsonUtil.objectToString(paramsMap);
    }


    public String getStatus(String host, int port, String jobId) {
        login(host, port);
        HttpRequest request =
                HttpRequest.get(String.format("http://%s:%d/pipeline/apis/v1beta1/runs/%s", host, port, jobId))
                        .header(Header.CONTENT_TYPE, "application/json");
        String body = request.execute().body();
        RunDetail runDetail = GsonUtil.gsonToBean(body, RunDetail.class);
        String status = runDetail.getRun().getStatus();
        // 在状态为null的时候，说明是刚创建出来的run，自行定义为Created状态
        return status == null ? "Running" : status;
    }

    public PipelinePage pageByPipeline(String host, int port, int pageSize) {
        login(host, port);
        HttpRequest request =
                HttpRequest.get(StrUtil.format("http://{}:{}/pipeline/apis/v1beta1/pipelines?page_size={}&sort_by" +
                                "=created_at%20desc", host, port, pageSize))
                        .header(Header.CONTENT_TYPE, "application/json");
        return gson.fromJson(request.execute().body(), PipelinePage.class);
    }

    @Data
    private static class RunDetail {
        private Run run;


        @Data
        private static class Run {
            private String id;
            private String name;
            private String status;
        }
    }


    @Data
    private static class Reference {
        private Key key = new Key();
        private String relationship;


        @Data
        private static class Key {
            private String id;
            private String type;
        }
    }
}
