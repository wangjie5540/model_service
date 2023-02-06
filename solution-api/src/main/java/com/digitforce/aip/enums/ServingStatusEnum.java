package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ServingStatusEnum {
    FINISHED("计算完成"),
    ON_SERVING("服务中"),
    ERROR("异常");

    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    ServingStatusEnum(String cname) {
        this.cname = cname;
    }
}
