package com.digitforce.aip.dto.qry;


import com.digitforce.framework.api.dto.Query;

/**
 * 通过方案id获取策略列表
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
public class ImplementationListBySolutionQry extends Query {
    private Long solutionId;

    public Long getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(Long solutionId) {
        this.solutionId = solutionId;
    }
}
