package com.digitforce.aip.dto.data;

import java.util.List;

/**
 * kubeflow pipeline的入参类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 16:30
 */
public class PipelineParameterDTO {
    private List<String> parameters;

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }
}
