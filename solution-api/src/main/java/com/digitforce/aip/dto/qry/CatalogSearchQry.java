package com.digitforce.aip.dto.qry;

import com.digitforce.framework.api.dto.Query;
import lombok.Data;

@Data
public class CatalogSearchQry extends Query {
    private String name;
}
