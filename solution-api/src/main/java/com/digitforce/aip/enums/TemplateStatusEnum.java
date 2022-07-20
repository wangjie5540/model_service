package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TemplateStatusEnum {
    ONLINE("已发布"),
    OFFLINE("未发布");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    TemplateStatusEnum(String cname) {
        this.cname = cname;
    }
}
