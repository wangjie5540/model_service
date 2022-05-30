package com.digitforce.aip.dto.qry;

import com.digitforce.framework.api.dto.Query;
import lombok.Data;

@Data
public class CatalogListQry extends Query {
    private String name;
    private Long pid;
    private Integer level;
}
