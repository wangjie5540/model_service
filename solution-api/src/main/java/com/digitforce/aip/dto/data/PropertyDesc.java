package com.digitforce.aip.dto.data;

public class PropertyDesc {
    private String name;
    private String cname;
    private String dataType;

    public PropertyDesc() {
    }

    public PropertyDesc(String name, String cname, String dataType) {
        this.name = name;
        this.cname = cname;
        this.dataType = dataType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
