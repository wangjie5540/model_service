package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SolutionStatusEnum {
    EXECUTING("执行中"),
    STOPPED("已停止"),
    TUNING("调参中"),
    READY("已就绪"),
    PUBLISHED("已发布"),
    ERROR("异常");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    SolutionStatusEnum(String cname) {
        this.cname = cname;
    }
}
