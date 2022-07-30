package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DataTypeEnum {
    STRING("字符串"),
    NUMERIC("数字"),
    DATETIME("日期");
    String cname;

    @JsonValue
    String getCname() {
        return cname;
    }

    DataTypeEnum(String cname) {
        this.cname = cname;
    }
}
