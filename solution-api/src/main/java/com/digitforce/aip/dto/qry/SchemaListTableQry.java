package com.digitforce.aip.dto.qry;

import com.digitforce.framework.api.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SchemaListTableQry extends Query {
    private String name;
}
