package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 算法名称枚举类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/19 17:15
 */
public enum AlgorithmEnum {
    DSSM("DSSM");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    AlgorithmEnum(String cname) {
        this.cname = cname;
    }
}
