package com.digitforce.aip.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.digitforce.aip.po.SolutionServingPO;
import lombok.Data;

/**
 * TODO
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:55
 */
@Data
@TableName(value = "solution_serving", autoResultMap = true)
public class SolutionServing extends SolutionServingPO {
}
