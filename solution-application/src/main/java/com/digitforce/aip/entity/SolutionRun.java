package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.aip.enums.RunStatusEnum;
import com.digitforce.aip.enums.SolutionRunTypeEnum;
import com.digitforce.framework.domain.TenantEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 方案执行表
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("solution_run")
public class SolutionRun extends TenantEntity<Long> implements Serializable {
    private static final long serialVersionUID = 4239051849196607086L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 方案id
     */
    private Long solutionId;

    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * pipeline id
     */
    private String pipelineId;

    /**
     * pipeline name
     */
    private String pipelineName;

    /**
     * kubeflow-pipeline的运行id
     */
    private String pRunId;

    /**
     * kebeflow-pipeline的运行名称
     */
    private String pRunName;

    /**
     * run状态
     */
    private RunStatusEnum status;

    /**
     * run错误信息
     */
    private String error;

    /**
     * 方案运行类型
     */
    private SolutionRunTypeEnum type;

    /**
     * 删除标志
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 调度时间
     */
    private LocalDateTime scheduleTime;

    /**
     * 结束时间
     */
    private LocalDateTime finishTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
