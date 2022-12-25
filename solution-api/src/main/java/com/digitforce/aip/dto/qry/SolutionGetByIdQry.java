package com.digitforce.aip.dto.qry;


import com.digitforce.framework.api.dto.Query;
import lombok.Data;

/**
 * 通过id查询方案实施实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
@Data
public class SolutionGetByIdQry extends Query {
    private static final long serialVersionUID = 4128095560830234864L;
    private Long id;
}
