package com.digitforce.aip.entity.dto.cmd;

import lombok.Data;

import java.util.List;

@Data
public class AutomlParams {
    private String namespace;
    private String experimentName;
    private AlgorithmSpec algorithmSpec;
    private ObjectiveSpec objectiveSpec;
    private List<Parameter> parameters;


    @Data
    public static class AlgorithmSpec {
        private String algorithm_name;
    }

    @Data
    public static class ObjectiveSpec {
        private String type;
        private Float goal;
        private String objective_metric_name;
        private List<String> additional_metric_names;
    }

    @Data
    public static class Parameter {
        private String name;
        private String parameter_type;
        private FeasibleSpace feasible_space;

        @Data
        public static class FeasibleSpace {
            private List<String> list;
        }
    }
}
