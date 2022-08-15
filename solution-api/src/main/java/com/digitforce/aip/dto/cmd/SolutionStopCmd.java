package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 停止执行实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "停止执行"
)
public class SolutionStopCmd extends Command {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
