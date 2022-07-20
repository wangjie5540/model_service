package com.digitforce.aip.enums;

import lombok.Getter;

@Getter
public enum TemplateStatusEnum {
    ONLINE("上线"),
    OFFLINE("下线");
    final String cname;

    TemplateStatusEnum(String cname) {
        this.cname = cname;
    }
}
