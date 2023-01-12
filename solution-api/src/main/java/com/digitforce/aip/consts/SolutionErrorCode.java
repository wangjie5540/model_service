package com.digitforce.aip.consts;

import com.digitforce.framework.api.exception.ErrorCode;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 15:16
 */
public enum SolutionErrorCode implements ErrorCode {
    SCENE_NAME_DUPLICATE("SCENE_NAME_DUPLICATE", "场景名称重复"),
    TEMPLATE_PARAMS_ERROR("TEMPLATE_PARAMS_ERROR", "模板参数错误，渲染异常"),
    SOLUTION_NOT_FOUND("SOLUTION_NOT_FOUND", "方案没有找到"),
    ;

    private final String errCode;
    private final String errDesc;

    SolutionErrorCode(String errCode, String errDesc) {
        this.errCode = errCode;
        this.errDesc = errDesc;
    }

    @Override
    public String getErrCode() {
        return errCode;
    }

    @Override
    public String getErrDesc() {
        return errDesc;
    }
}
