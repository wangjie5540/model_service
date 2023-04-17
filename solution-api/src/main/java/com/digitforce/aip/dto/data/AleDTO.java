package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class AleDTO {
    private Long instanceId;
    private Object targets;

    @Data
    public static class Target {
        private String target;
        private List<FeatureContribution> featureContributions;
    }

    @Data
    public static class FeatureContribution {
        private List<String> x;
        private List<Double> y;
    }
}
