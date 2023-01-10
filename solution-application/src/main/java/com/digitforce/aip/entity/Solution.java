package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.digitforce.aip.enums.SceneTypeEnum;
import com.digitforce.aip.enums.SolutionStatusEnum;
import com.digitforce.framework.domain.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

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
@TableName(value = "solution", autoResultMap = true)
public class Solution extends AggregateRoot<Long> implements Serializable {
    private static final long serialVersionUID = -5749822666609826153L;
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
     * 场景id
     */
    private Long sceneId;

    /**
     * 场景类型
     */
    private SceneTypeEnum sceneType;

    /**
     * 场景名称
     */
    private String sceneName;

    /**
     * 自动调参任务的runId
     */
    private String aRunId;

    /**
     * kubeflow pipelineId
     */
    private String pipelineId;

    /**
     * kubeflow pipelineName
     */
    private String pipelineName;

    /**
     * pipeline 训练参数模板
     */
    private String trainTemplate;

    /**
     * pipeline 自动调参模板
     */
    private String automlTemplate;

    /**
     * pipeline 参数模板
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> templateParams;

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
     * 表单信息
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> formInfo;

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
    private Boolean autoML;

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
