package com.digitforce.aip.po;

import com.digitforce.framework.domain.TenantEntity;
import lombok.Data;

@Data
public class CatalogPO extends TenantEntity<Long> {

    private String name;
    private Long pid;
    private String code;
    private Integer level;
    private Integer status;

}
