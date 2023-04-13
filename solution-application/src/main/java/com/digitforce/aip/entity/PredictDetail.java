package com.digitforce.aip.entity;

import lombok.Data;

@Data
public class PredictDetail {
    private Long instanceId;
    private String userId;
    private Double score;
}
