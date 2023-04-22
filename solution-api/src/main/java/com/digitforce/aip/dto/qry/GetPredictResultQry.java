package com.digitforce.aip.dto.qry;

import com.digitforce.aip.enums.ScoreRangeType;
import lombok.Data;

import java.util.List;

@Data
public class GetPredictResultQry {
    private Long instanceId;
    private ScoreRangeType scoreRangeType;
    private List<Object> values;
}
