package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务实例表
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "serving_instance", autoResultMap = true)
public class ServingInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 租户id
     */
    private Integer tenantId;

    /**
     * 场景id
     */
    private Long sceneId;

    /**
     * 方案id
     */
    private Long solutionId;

    /**
     * 服务id
     */
    private Long servingId;

    /**
     * kubeflow-pipeline的运行id
     */
    private String pRunId;

    /**
     * kebeflow-pipeline的运行名称
     */
    private String pRunName;

    /**
     * kebeflow-pipeline运行参数
     */
    private String pipelineParams;

    /**
     * 状态
     */
    private ServingInstanceStatusEnum status;
    /**
     * 模型版本
     */
    private Long modelVersion;

    /**
     * 耗时秒数
     */
    private Long consumption;

    /**
     * 结果
     */
    private String result;

    /**
     * ale值
     */
    private String ale;

    /**
     * 创建用户
     */
    private String createUser;

    /**
     * 更新用户
     */
    private String updateUser;

    /**
     * 逻辑删除标志
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
