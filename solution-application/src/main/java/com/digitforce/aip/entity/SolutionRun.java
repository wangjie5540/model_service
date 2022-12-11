package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2022-12-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("solution_run")
public class SolutionRun implements Serializable {
    private static final long serialVersionUID = 4239051849196607086L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
    private String status;

    /**
     * run错误信息
     */
    private String error;

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


}
