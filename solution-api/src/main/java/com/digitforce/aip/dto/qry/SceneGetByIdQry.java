package com.digitforce.aip.dto.qry;

import com.digitforce.framework.api.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SceneGetByIdQry extends Query {
    private static final long serialVersionUID = -397810999839582363L;
    private Long id;
}
