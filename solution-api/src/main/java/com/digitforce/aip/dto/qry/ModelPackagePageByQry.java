package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.ModelPackageDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ModelPackagePageByQry extends PageQuery<ModelPackageDTO> {
    private static final long serialVersionUID = -1363537520198694260L;
    private ModelPackageDTO likeClause;
}
