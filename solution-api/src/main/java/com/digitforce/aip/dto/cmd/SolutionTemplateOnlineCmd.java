package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;

/**
 * online和offline使用的实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 10:30
 */
public class SolutionTemplateOnlineCmd extends Command {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
