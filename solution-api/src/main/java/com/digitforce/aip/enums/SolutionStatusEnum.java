package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SolutionStatusEnum {
    NOT_EXECUTE("未执行"),
    EXECUTING("执行中"),
    STOPPING("停止中"),
    FINISHED("执行完成"),
    ONLINE("上线"),
    FAILED("执行失败");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    SolutionStatusEnum(String cname) {
        this.cname = cname;
    }
}
