package com.digitforce.aip.dto.qry;

import lombok.Data;

@Data
public class StreamPredictDetailQry {
    private Long instanceId;
    private Double minScore;
    private Double maxScore;
    private Long total;
}
