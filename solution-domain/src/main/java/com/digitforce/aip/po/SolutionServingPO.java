package com.digitforce.aip.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.digitforce.aip.enums.ServingTypeEnum;
import com.digitforce.framework.domain.TenantEntity;
import lombok.Data;

/**
 * 方案实施持久化类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
public class SolutionServingPO extends TenantEntity<Long> {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long solutionId;
    private Long templateId;
    private String config;
    private ServingTypeEnum servingType;
}
