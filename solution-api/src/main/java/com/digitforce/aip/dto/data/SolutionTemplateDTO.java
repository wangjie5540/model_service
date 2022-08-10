package com.digitforce.aip.dto.data;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.enums.TemplateStatusEnum;
import com.digitforce.framework.domain.AggregateRoot;
import lombok.Data;

/**
 * 方案模板实体定义类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class SolutionTemplateDTO extends AggregateRoot<Long> {
    private String name;
    private String scene;
    private String description;
    private String pipelineId;
    private String pipelineName;
    private Integer browseCount;
    private Integer applyCount;
    private PipelineDataSource dataSource;
    // TODO 后续将迭代调度机制
    private String schedule = GlobalConstant.DEFAULT_CRON;
    private PipelineParameterDTO pipelineParameter;
    private TemplateStatusEnum status;
}
