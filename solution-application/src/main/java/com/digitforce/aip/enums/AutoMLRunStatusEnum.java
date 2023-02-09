package com.digitforce.aip.enums;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 自动调参运行状态枚举
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2023/01/09 22:36
 */
@Getter
@Slf4j
public enum AutoMLRunStatusEnum {
    Running(0),
    Success(1),
    Failed(2),
    skip(3),
    error(4),
    Timeout(5),
    unKnow(6),
    NotReady(9),
    NotExist(10),
    ;
    final Integer code;

    public static AutoMLRunStatusEnum getEnum(Integer code) {
        log.info("AutoMLRunStatusEnum getEnum code:{}", code);
        switch (code) {
            case 0:
                return Running;
            case 1:
                return Success;
            case 2:
                return Failed;
            case 3:
                return skip;
            case 4:
                return error;
            case 5:
                return Timeout;
            case 9:
                return NotReady;
            case 10:
                return NotExist;
            case 6:
            default:
                log.warn("unknown code {}", code);
                return unKnow;
        }
    }

    AutoMLRunStatusEnum(Integer code) {
        this.code = code;
    }
}
