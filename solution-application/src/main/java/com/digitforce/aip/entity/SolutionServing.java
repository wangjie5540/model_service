package com.digitforce.aip.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@Accessors(chain = true)
@TableName("solution_serving")
public class SolutionServing implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 方案模板id
     */
    private Long sceneId;

    /**
     * 方案id
     */
    private Long solutionId;

    /**
     * 筛选范围
     */
    private String selection;

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
