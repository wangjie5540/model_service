package com.digitforce.aip.dto.data;

import java.util.List;

/**
 * 模板列表实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
public class TemplateStatusListDTO {
    private List<TemplateStatus> statusList;

    public List<TemplateStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<TemplateStatus> statusList) {
        this.statusList = statusList;
    }
}
