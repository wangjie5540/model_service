package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SceneStatusEnum {
    //    DRAFT("草稿"),
    ONLINE("已发布"),
    OFFLINE("待发布");
    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    SceneStatusEnum(String cname) {
        this.cname = cname;
    }
}
