package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.TemplateStatusEnum;

public class TemplateStatus {
    private TemplateStatusEnum status;
    private String cname;

    public TemplateStatus() {
    }

    public TemplateStatus(TemplateStatusEnum status, String cname) {
        this.status = status;
        this.cname = cname;
    }

    public TemplateStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TemplateStatusEnum status) {
        this.status = status;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
