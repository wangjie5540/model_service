package com.digitforce.aip.dto.qry;

import com.digitforce.framework.api.dto.Query;
import lombok.Data;

@Data
public class CatalogPageQry extends Query {
    private Integer pageNum;
    private Integer pageSize;
    private String name;

}
