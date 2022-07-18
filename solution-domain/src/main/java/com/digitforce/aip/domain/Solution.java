package com.digitforce.aip.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.aip.po.SolutionPO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("solution")
public class Solution extends SolutionPO {
}
