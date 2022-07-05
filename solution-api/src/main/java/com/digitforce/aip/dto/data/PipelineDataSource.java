package com.digitforce.aip.dto.data;

import java.util.List;

public class PipelineDataSource {
    private List<PropertyDesc> userData;
    private List<PropertyDesc> goodsData;
    private List<PropertyDesc> orderData;
    private List<TrafficDesc> trafficData;

    public List<PropertyDesc> getUserData() {
        return userData;
    }

    public void setUserData(List<PropertyDesc> userData) {
        this.userData = userData;
    }

    public List<PropertyDesc> getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(List<PropertyDesc> goodsData) {
        this.goodsData = goodsData;
    }

    public List<PropertyDesc> getOrderData() {
        return orderData;
    }

    public void setOrderData(List<PropertyDesc> orderData) {
        this.orderData = orderData;
    }

    public List<TrafficDesc> getTrafficData() {
        return trafficData;
    }

    public void setTrafficData(List<TrafficDesc> trafficData) {
        this.trafficData = trafficData;
    }
}
