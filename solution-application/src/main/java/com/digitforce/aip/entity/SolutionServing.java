package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.digitforce.aip.enums.SceneTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 方案服务表
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "solution_serving", autoResultMap = true)
public class SolutionServing implements Serializable {
    private static final long serialVersionUID = -4394811474561493119L;
    /**
     * 方案服务id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 场景id
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
     * kubeflow pipeline需要的服务参数模板
     */
    private String pipelineTemplate;

    /**
     * 服务参数模板渲染需要的参数
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Map<String, Object> templateParams;

    /**
     * kubeflow pipeline参数
     */
    @Deprecated
    private String pipelineParams;

    /**
     * 场景类型
     */
    private SceneTypeEnum sceneType;

    /**
     * 方案id
     */
    private Long solutionId;

    /**
     * 方案服务类型
     */
    private String servingType;

    /**
     * 表单信息
     */
    private String formInfo;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 服务状态
     */
    private String status;

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
