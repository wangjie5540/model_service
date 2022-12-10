package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.aip.enums.SceneTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("scene")
public class Scene implements Serializable {
    private static final long serialVersionUID = 2966524253410979719L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 场景名称
     */
    private String name;

    /**
     * 生效的version_id
     */
    private Long vidInUse;

    /**
     * 目标系统
     */
    private String targetSystem;

    /**
     * 场景类型：CustomerAI
     */
    private SceneTypeEnum sceneType;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 方案数量
     */
    private Integer solutionCount;

    /**
     * 服务数量
     */
    private Integer servingCount;

    /**
     * 场景描述
     */
    private String description;

    /**
     * 场景状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;


}
