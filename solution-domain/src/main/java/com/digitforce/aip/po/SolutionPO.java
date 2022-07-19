package com.digitforce.aip.po;

import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.framework.domain.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.temporal.ChronoUnit;

/**
 * 方案持久化类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SolutionPO extends TenantEntity<Long> {
    private Long id;
    private Long templateId;
    private Long taskId;
    private Long taskInstanceId;
    private String scene;
    private String name;
    private String selection;
    private String description;
    private String dataSource;
    private String schedule;
    private SolutionStatusEnum status;
    private Integer timeRange;
    private ChronoUnit timeUnit;
}
