package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.enums.TemplateStatusEnum;
import lombok.Data;

/**
 * 方案修改实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 11:33
 */
@Data
public class SolutionTemplateModifyCmd {
    private Long id;
    private String name;
    private String scene;
    private String description;
    private TemplateStatusEnum status;
}
