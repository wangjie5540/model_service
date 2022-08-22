package com.digitforce.aip.enums;

public enum DataTypeEnum {
    STRING("字符串"),
    NUMERIC("数字"),
    BOOL("布尔"),
    ARRAY("数组"),
    DATETIME("日期");
    final String cname;

    String getCname() {
        return cname;
    }

    DataTypeEnum(String cname) {
        this.cname = cname;
    }
}
