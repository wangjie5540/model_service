package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionPageByQry extends PageQuery<SolutionDTO> {
    private static final long serialVersionUID = -2994491485109248391L;
    private SolutionDTO likeClause;
}
