package com.digitforce.aip.dto.data;

import com.digitforce.framework.api.dto.TenantDTO;
import lombok.Data;

@Data
public class CatalogDTO extends TenantDTO<Long> {
    private String name;
    private Integer level;
    private Long pid;
    private String code;
}
