package com.digitforce.aip.po;

import com.digitforce.framework.domain.TenantEntity;
import lombok.Data;

/**
 * 方案模板持久化类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
public class SolutionTemplatePO extends TenantEntity<Long> {
    private String name;
    private String scene;
    private String pipelineId;
    private String pipelineName;
    private String description;
    private Integer browseCount;
    private Integer applyCount;
    private Boolean status;
}
