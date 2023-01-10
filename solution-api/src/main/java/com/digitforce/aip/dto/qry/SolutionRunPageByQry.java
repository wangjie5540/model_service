package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.SolutionRunDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionRunPageByQry extends PageQuery<SolutionRunDTO> {
    private static final long serialVersionUID = -1417371778912082811L;
    private SolutionRunDTO likeClause;
}
