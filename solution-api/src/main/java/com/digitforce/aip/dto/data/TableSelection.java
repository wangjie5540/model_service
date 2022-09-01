package com.digitforce.aip.dto.data;

import lombok.Data;

@Data
public class TableSelection {
    private String table;
    private Filter filter;
    private String filterSql;
}
