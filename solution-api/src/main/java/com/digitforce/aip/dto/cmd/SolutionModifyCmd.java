package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 编辑方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "编辑方案实体类")
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionModifyCmd extends Command {
    private static final long serialVersionUID = 407632844334756249L;
    private Long id;
    private String title;
    private String description;
    private Map<String, Object> formInfo;
    private Boolean automl;
}
