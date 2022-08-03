package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum FunctionEnum {
    EQ("等于"),
    NE("不等于"),
    GT("大于"),
    GE("大于等于"),
    LT("小于"),
    LE("小于等于"),
    IS_NULL("为空"),
    IS_NOT_NULL("不为空"),
    INCLUDE("包含"),
    NOT_INCLUDE("不包含"),
    BETWEEN("区间");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    FunctionEnum(String cname) {
        this.cname = cname;
    }
}
