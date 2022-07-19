package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;

import java.util.List;

/**
 * 方案模板批量删除实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 11:29
 */
public class SolutionTemplateBatchDeleteCmd extends Command {
    private List<Long> ids;

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
