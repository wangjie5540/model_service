package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.aip.enums.SceneTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@TableName("solution_serving")
public class SolutionServing implements Serializable {
    private static final long serialVersionUID = 3551630260535762108L;
    /**
     * 方案服务id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
    private Long sceneName;

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
