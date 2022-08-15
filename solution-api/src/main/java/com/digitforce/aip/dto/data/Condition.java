package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.DataTypeEnum;
import com.digitforce.aip.enums.FunctionEnum;
import lombok.Data;

import java.util.List;

@Data
public class Condition {
    private String field;
    private DataTypeEnum dataType;
    private FunctionEnum function;
    private List<Object> params;
}
