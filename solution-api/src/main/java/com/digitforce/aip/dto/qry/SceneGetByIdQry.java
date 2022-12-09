package com.digitforce.aip.dto.qry;

import com.digitforce.framework.api.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SceneGetByIdQry extends Query {
    private Long id;
}
