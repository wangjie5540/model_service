package com.digitforce.aip.dto.qry;

import lombok.Data;

@Data
public class PredictDetailPageByQry {
    private Long instanceId;
    private Long total;
    private Integer pageNum;
    private Integer pageSize;
    private Double minScore;
    private Double maxScore;
    private String userId;
}
