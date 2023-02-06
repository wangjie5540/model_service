package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.digitforce.aip.dto.data.ModelMetric;
import com.digitforce.aip.enums.ResourceTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@TableName(value = "model", autoResultMap = true)
public class Model implements Serializable {
    private static final long serialVersionUID = 6778989161261732670L;
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
     * 模型包id
     */
    private Long packageId;

    /**
     * 名称
     */
    private String name;

    /**
     * 展示名称
     */
    private String cname;

    /**
     * model or data
     */
    private ResourceTypeEnum resourceType;

    /**
     * 单位MB
     */
    private Float size;

    /**
     * 模型指标
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ModelMetric> metricsList;

    /**
     * 逻辑删除
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
