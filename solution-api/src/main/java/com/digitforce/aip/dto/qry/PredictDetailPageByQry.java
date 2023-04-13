package com.digitforce.aip.dto.qry;

import lombok.Data;

@Data
public class PredictDetailPageByQry {
    private Long instanceId;
    private Long total;
    private Double minScore;
    private Double maxScore;
}
