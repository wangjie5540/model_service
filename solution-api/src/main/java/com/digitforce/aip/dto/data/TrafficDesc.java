package com.digitforce.aip.dto.data;

public class TrafficDesc {
    private String eventType;
    private PropertyDesc eventTime;
    private PropertyDesc userId;
    private PropertyDesc where;
    private PropertyDesc goodsId;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public PropertyDesc getEventTime() {
        return eventTime;
    }

    public void setEventTime(PropertyDesc eventTime) {
        this.eventTime = eventTime;
    }

    public PropertyDesc getUserId() {
        return userId;
    }

    public void setUserId(PropertyDesc userId) {
        this.userId = userId;
    }

    public PropertyDesc getWhere() {
        return where;
    }

    public void setWhere(PropertyDesc where) {
        this.where = where;
    }

    public PropertyDesc getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(PropertyDesc goodsId) {
        this.goodsId = goodsId;
    }
}
