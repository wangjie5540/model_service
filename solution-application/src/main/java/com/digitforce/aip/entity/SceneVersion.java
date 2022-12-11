package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.framework.domain.AggregateRoot;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 场景表
 * </p>
 *
 * @author wangtonggui
 * @since 2022-12-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("scene_version")
public class SceneVersion extends AggregateRoot<Long> implements Serializable {

    private static final long serialVersionUID = 8452170699118419289L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场景版本名称
     */
    private String name;

    /**
     * kubeflow的pipeline_id
     */
    private String pipelineId;

    /**
     * kubeflow的pipeline_name
     */
    private String pipelineName;

    /**
     * 使用的算法，逗号分割
     */
    private String algorithm;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;
}
