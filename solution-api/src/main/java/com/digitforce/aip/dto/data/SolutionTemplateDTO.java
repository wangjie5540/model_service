package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.TemplateStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 方案模板实体定义类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class SolutionTemplateDTO {
    private Long id;
    private String name;
    private String scene;
    private String description;
    private String pipelineId;
    private String pipelineName;
    private Integer browseCount;
    private Integer applyCount;
    private PipelineDataSource dataSource;
    private PipelineParameterDTO pipelineParameter;
    private TemplateStatusEnum status;
    private String createUser;
    private String updateUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
