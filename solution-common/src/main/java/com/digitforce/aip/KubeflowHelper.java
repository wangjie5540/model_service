package com.digitforce.aip;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.PipelineDTO;
import com.digitforce.framework.util.GsonUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.URI;
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
public class KubeflowHelper {
    private static final Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();

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
            "http://%s:%d/dex/auth?client_id=kubeflow-oidc-authservice&redirect_uri=/login/oidc&response_type=code&scope=profile+email+groups+openid&amp;state=%s",
            host, port, state));
        query = getQuery(rep);
        Map<String, Object> loginData = new HashMap<>();
        loginData.put("login", CommonConst.KUBEFLOW_LOGIN_USER);
        loginData.put("password", CommonConst.KUBEFLOW_LOGIN_PASSWORD);
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


    public PipelineDTO getPipelineDetail(String host, int port, String pipelineId) {
        login(host, port);
        String body = HttpRequest.get(String.format(
            "http://%s:%d/pipeline/apis/v1beta1/pipelines/%s", host, port, pipelineId)).execute().body();
        return gson.fromJson(body, PipelineDTO.class);
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

    public String triggerRun(String host, int port, String name, String experimentId, String pipelineId,
        List<Map<String, Object>> pipelineParameters) {
        login(host, port);
        HttpRequest httpRequest = HttpRequest.post(String.format("http://%s:%d/pipeline/apis/v1beta1/runs", host, port))
            .body(generateBody(name, experimentId, pipelineId, pipelineParameters));
        RunDetail runDetail = GsonUtil.gsonToBean(httpRequest.execute().body(), RunDetail.class);
        return runDetail.run.getId();
    }

    public String generateBody(String name, String experimentId, String pipelineId,
        List<Map<String, Object>> pipelineParameters) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("name", name);
        paramsMap.put("description", "");
        Map<String, Object> pipelineSpecMap = new HashMap<>();
        pipelineSpecMap.put("parameters", pipelineParameters);
        paramsMap.put("pipeline_spec", pipelineSpecMap);
        List<Reference> referenceList = new ArrayList<>();
        Reference pipelineRef = new Reference();
        pipelineRef.key.id = pipelineId;
        pipelineRef.key.type = "PIPELINE_VERSION";
        pipelineRef.relationship = "CREATOR";
        referenceList.add(pipelineRef);
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
        RunDetail runDetail = GsonUtil.gsonToBean(request.execute().body(), RunDetail.class);
        return runDetail.getRun().getStatus();
    }

    private static class RunDetail {
        private Run run;

        public Run getRun() {
            return run;
        }

        public void setRun(Run run) {
            this.run = run;
        }

        private static class Run {
            private String id;
            private String name;
            private String status;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }


    private static class Reference {
        private Key key = new Key();
        private String relationship;


        private static class Key {
            private String id;
            private String type;
        }
    }


    @Data
    @AllArgsConstructor
    private static class Parameter {
        private String name;
        private String value;
    }

    public static void main(String[] args) {
//        HttpCookie cookie = getCookie("172.21.32.143", 30000);
//        String body = HttpRequest.get(
//            "http://172.21.32.143:30000/pipeline/apis/v1beta1/pipelines/c4b79a5a-8bf1-429b-b01e-15a0ec992465").cookie(
//            cookie).execute().body();
//        System.out.println(body);
//        System.out.println(GsonUtil.gsonToBean(body, PipelineDTO.class));

        List<Map<String, Object>> pipeParams = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "url");
        map.put("value", "https://storage.googleapis.com/ml-pipeline-playground/iris-csv-files.tar.gz");
        pipeParams.add(map);
        map = new HashMap<>();
        map.put("name", "d");
        map.put("value", "xxxxxxxxxxx");
        pipeParams.add(map);
        String my_test_name = KubeflowHelper.generateBody("my_test_name", "f56c9dfe-d57d-444a-bc61-01ff9fe03a5d",
            "83e8b077-827c-4db0-897d-9eb146313f95", pipeParams);

        System.out.println(triggerRun("172.21.32.143", 30000, "run_name_1231", "f56c9dfe-d57d-444a-bc61-01ff9fe03a5d",
            "83e8b077-827c-4db0-897d-9eb146313f95", pipeParams));
        System.out.println(getStatus("172.21.32.143", 30000, "fbbfd47a-4fa3-4701-878f-94a451f7c4d0"));
    }
}
