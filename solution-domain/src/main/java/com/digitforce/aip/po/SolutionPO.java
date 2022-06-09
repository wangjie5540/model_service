package com.digitforce.aip.po;

import com.digitforce.framework.domain.TenantEntity;
import lombok.Data;

/**
 * 方案持久化类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
public class SolutionPO extends TenantEntity<Long> {
    private Long id;
    private String type;
    private String cname;
    private String description;
    private String scene;
    private String pipelineId;
    private Boolean status;
    private Boolean deleted;
}
