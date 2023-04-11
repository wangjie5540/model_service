package com.digitforce.aip.dto.data;

import lombok.Data;

@Data
public class PredictResultDTO {
    private Long total;
    private Double ratio;
    private Double minScore;
    private Double maxScore;
}
