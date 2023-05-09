package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServingInstancePageByQry extends PageQuery<ServingInstanceDTO> {
    private static final long serialVersionUID = -4913074952006696804L;
    private ServingInstanceDTO likeClause;
    private Boolean myOwn;
}
