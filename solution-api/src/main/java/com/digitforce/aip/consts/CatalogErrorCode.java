package com.digitforce.aip.consts;


import com.digitforce.framework.api.exception.ErrorCode;

public enum CatalogErrorCode implements ErrorCode {
    BIZ_ERROR("BIZ_ERROR", "业务逻辑错误"),
    LEVEL_ERROR("LEVEL_ERROR", "类目层级不一致"),
    STATUS_ERROR("STATUS_ERROR", "类目状态不正确");

    private final String errCode;
    private final String errDesc;

    CatalogErrorCode(String errCode, String errDesc) {
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
