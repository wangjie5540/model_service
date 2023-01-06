package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum ServingInstanceStatusEnum {
    PREDICTING("预测中"),
    FINISHED("完成"),
    ERROR("异常"),
    ;
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    ServingInstanceStatusEnum(String cname) {
        this.cname = cname;
    }
}
