package com.digitforce.aip.dto.data;

import lombok.Data;

@Data
public class ServingDynamicFormDTO {
    private Object dataSource;
    private Object trainSample;
    private Object modelHyperParameter;
}
