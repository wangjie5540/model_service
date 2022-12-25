package com.digitforce.aip.dto.qry;


import com.digitforce.framework.api.dto.Query;
import lombok.Data;

/**
 * 通过方案id获取策略列表
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
@Data
public class SolutionListBySolutionQry extends Query {
    private static final long serialVersionUID = -4299687292356420554L;
    private Long solutionId;
}
