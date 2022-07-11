package com.digitforce.aip.dto.qry;


import com.digitforce.framework.api.dto.Query;

/**
 * 通过pipelineId查询详情
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
public class PipelineGetByIdQry extends Query {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
