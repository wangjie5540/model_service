package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.FunctionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * filter function实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FunctionDTO {
    private List<FunctionEnum> functions;
}
