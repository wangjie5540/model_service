package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SolutionRunTypeEnum {
    DEBUG("调试类"),
    DEPLOY("部署类");

    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    SolutionRunTypeEnum(String cname) {
        this.cname = cname;
    }
}
