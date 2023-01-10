package com.digitforce.aip.dto.qry;

import com.digitforce.aip.enums.StageEnum;
import lombok.Data;

@Data
public class SceneGetFromQry {
    private Long sceneId;
    private StageEnum type;
}
