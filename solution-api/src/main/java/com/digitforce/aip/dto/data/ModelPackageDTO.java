package com.digitforce.aip.dto.data;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 模型包实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class ModelPackageDTO {
    private Long id;
    private String name;
    private String solutionTitle;
    private String algorithm;
    private String system;
    private Integer liftCycle;
    private LocalDateTime createTime;
    private List<ModelDTO> fittedModelList;
}
