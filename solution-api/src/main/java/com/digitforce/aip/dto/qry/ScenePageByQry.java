package com.digitforce.aip.dto.qry;

import com.digitforce.aip.dto.data.SceneDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ScenePageByQry extends PageQuery<SceneDTO> {
}
