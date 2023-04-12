package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class PredictResultDTO {
    private Long total;
    private Double ratio;
    private ScoreRange scoreRange;
    private List<Interval> baseIntervals;
    private List<Interval> targetIntervals;

    @Data
    public static class ScoreRange {
        private Double minScore;
        private Double maxScore;
    }

    @Data
    public static class Interval {
        private String cname;
        private Long total;
    }
}
