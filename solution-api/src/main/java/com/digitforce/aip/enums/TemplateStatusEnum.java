package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TemplateStatusEnum {
    ONLINE("上线"),
    OFFLINE("下线");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    TemplateStatusEnum(String cname) {
        this.cname = cname;
    }
}
