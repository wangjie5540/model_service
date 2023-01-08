package com.digitforce.aip.dto.cmd;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;

/**
 * 新增方案服务实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(description = "新增方案服务实体类")
@Data
public class SolutionServingAddCmd {
    private static final long serialVersionUID = 4184177917444677405L;
    private String title;
    private Long sceneId;
    private Long solutionId;
    private Object formInfo;
    private Map<String, Object> templateParams;
}
