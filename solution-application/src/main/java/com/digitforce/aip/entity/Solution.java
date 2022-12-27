package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.framework.domain.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 方案表
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("solution")
public class Solution extends AggregateRoot<Long> implements Serializable {
    private static final long serialVersionUID = 9085508804053336799L;
    /**
     * 方案主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 方案标题
     */
    private String title;

    /**
     * 模板主键id
     */
    private Long sceneId;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * kubeflow pipelineId
     */
    private String pipelineId;

    /**
     * kubeflow pipelineName
     */
    private String pipelineName;

    /**
     * pipeline params
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private String pipelineParams;

    /**
     * 应用系统
     */
    private String system;

    /**
     * 方案状态
     */
    private SolutionStatusEnum status;

    /**
     * cron表达式
     */
    private String cron;

    /**
     * cron描述
     */
    private String cronDesc;

    /**
     * 方案描述
     */
    private String description;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 删除标志
     */
    private Boolean deleted;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
