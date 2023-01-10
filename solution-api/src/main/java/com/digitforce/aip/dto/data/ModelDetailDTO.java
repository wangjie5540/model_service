package com.digitforce.aip.dto.data;

import com.digitforce.framework.api.dto.PageView;
import lombok.Data;

import java.util.List;

@Data
public class ModelDetailDTO {
    private List<String> metricColumnList;
    private PageView<ModelDTO> pageView;
}
