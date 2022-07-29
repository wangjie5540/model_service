package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.RelationEnum;
import lombok.Data;

import java.util.List;

@Data
public class Filter {
    private RelationEnum relation;
    private List<Condition> conditions;
    private List<Filter> filters;
}
