package com.digitforce.aip.utils;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.PipelineDTO;
import com.digitforce.framework.util.GsonUtil;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.net.HttpCookie;
import java.net.URI;
import java.util.HashMap;
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
     * 获取kubeflow登录的cookie
     *
     * @param host kubeflow服务器的host
     * @param port kubeflow服务器的port
     * @return cookie对象
     */
    public HttpCookie getCookie(String host, int port) {
        String rep = HttpUtil.get("http://172.21.32.143:30000");
        String query = getQuery(rep);
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
        return HttpRequest.get(
            String.format("http://%s:%d/login/oidc?code=%s&state=%s", host, port, code, state)).execute().getCookie(
            "authservice_session");
    }


    public PipelineDTO getPipelineDetail(String host, int port, String pipelineId) {
        HttpCookie cookie = getCookie(host, port);
        String body = HttpRequest.get(String.format(
            "http://%s:%d/pipeline/apis/v1beta1/pipelines/%s", host, port, pipelineId)).cookie(cookie).execute().body();
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

    public static void main(String[] args) {
        HttpCookie cookie = getCookie("172.21.32.143", 30000);
        String body = HttpRequest.get(
            "http://172.21.32.143:30000/pipeline/apis/v1beta1/pipelines/c4b79a5a-8bf1-429b-b01e-15a0ec992465").cookie(
            cookie).execute().body();
        System.out.println(body);
        System.out.println(GsonUtil.gsonToBean(body, PipelineDTO.class));
    }
}
