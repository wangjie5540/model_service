package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResourceTypeEnum {
    MODEL("模型"),
    DATA("数据");

    private final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    ResourceTypeEnum(String cname) {
        this.cname = cname;
    }
}
