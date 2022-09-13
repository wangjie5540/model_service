package com.digitforce.aip.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {
    private static final Gson gson;

    static {
        // TODO: 后续修改不再进行统一封装
        gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
    }

    public Gson getGson() {
        return gson;
    }
}
