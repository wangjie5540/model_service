package com.digitforce.aip.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

/**
 * 场景类型枚举
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/09
 */
@Getter
public enum SceneTypeEnum {
    CUSTOMER_AI("CustomerAI");

    final String cname;

    @JsonValue
    public String getCname() {
        return cname;
    }

    SceneTypeEnum(String cname) {
        this.cname = cname;
    }
}
