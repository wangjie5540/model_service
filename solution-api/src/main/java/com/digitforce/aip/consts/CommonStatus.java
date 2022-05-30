package com.digitforce.aip.consts;


import com.digitforce.framework.api.consts.Typable;

import java.util.HashMap;
import java.util.Map;

public enum CommonStatus implements Typable<Integer> {
    DEF(0, "默认"),
    ON(1, "启用"),
    OFF(4, "停用");

    private int code;
    private String desc;

    CommonStatus(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public boolean isValid(Integer code) {
        return types.containsKey(code);
    }


    private static Map<Integer, CommonStatus> types = new HashMap<>();

    static {
        for (CommonStatus type : CommonStatus.values()) {
            types.put(type.getCode(), type);
        }
    }
}
