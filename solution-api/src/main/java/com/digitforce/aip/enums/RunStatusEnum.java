package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum RunStatusEnum {
    Running("训练中"),
    Succeeded("完成"),
    Failed("异常"),
    Stopped("已停止"),
    Error("错误"),
    ;
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    RunStatusEnum(String cname) {
        this.cname = cname;
    }
}
