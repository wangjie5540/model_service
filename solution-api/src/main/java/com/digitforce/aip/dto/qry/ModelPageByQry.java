package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.ModelDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModelPageByQry extends PageQuery<ModelDTO> {
    private static final long serialVersionUID = -9145421465550913156L;
    private ModelDTO likeClause;
}
