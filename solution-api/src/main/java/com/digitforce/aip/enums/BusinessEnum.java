package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 行业枚举
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/19 17:15
 */
public enum BusinessEnum {
    SECURITIES("证券");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    BusinessEnum(String cname) {
        this.cname = cname;
    }
}
