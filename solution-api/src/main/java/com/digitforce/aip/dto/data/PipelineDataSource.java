package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class PipelineDataSource {
    private List<PropertyDesc> userData;
    private List<PropertyDesc> goodsData;
    private List<PropertyDesc> orderData;
    private List<PropertyDesc> trafficData;
}
