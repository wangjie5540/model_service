package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.TemplateStatusEnum;

import java.util.List;

/**
 * 模板列表实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
public class TemplateStatusListDTO {
    private List<TemplateStatusEnum> status;

    public List<TemplateStatusEnum> getStatus() {
        return status;
    }

    public void setStatus(List<TemplateStatusEnum> status) {
        this.status = status;
    }
}
