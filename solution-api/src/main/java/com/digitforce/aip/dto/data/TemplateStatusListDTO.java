package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.TemplateStatusEnum;
import lombok.Data;

import java.util.List;

/**
 * 模板列表实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
public class TemplateStatusListDTO {
    private List<TemplateStatusEnum> statusList;
}
