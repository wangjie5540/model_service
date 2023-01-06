package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.SolutionServingDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionServingPageByQry extends PageQuery<SolutionServingDTO> {
    private static final long serialVersionUID = -2354522192090216154L;
    private SolutionServingDTO likeClause;
}
