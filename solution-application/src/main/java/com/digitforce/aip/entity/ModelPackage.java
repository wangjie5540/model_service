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
 *
 * </p>
 *
 * @author wangtonggui
 * @since 2023-01-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName(value = "model_package", autoResultMap = true)
public class ModelPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 模型包id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 方案执行id
     */
    private Long solutionRunId;

    /**
     * 方案名称
     */
    private String solutionTitle;

    /**
     * 来源系统
     */
    private String system;

    /**
     * 模型包名称
     */
    private String name;

    /**
     * 生存周期
     */
    private Integer lifecycle;

    /**
     * 模型数据路径
     */
    private String path;

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
