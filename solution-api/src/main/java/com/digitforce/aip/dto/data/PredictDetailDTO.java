package com.digitforce.aip.dto.data;

import lombok.Data;

@Data
public class PredictDetailDTO {
    private Long instanceId;
    private String userId;
    private Double score;
}
