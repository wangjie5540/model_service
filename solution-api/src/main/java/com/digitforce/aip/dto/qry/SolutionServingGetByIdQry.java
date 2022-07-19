package com.digitforce.aip.dto.qry;


import com.digitforce.framework.api.dto.PageQuery;

/**
 * 通过id查询方案实施实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
public class SolutionServingGetByIdQry extends PageQuery {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
