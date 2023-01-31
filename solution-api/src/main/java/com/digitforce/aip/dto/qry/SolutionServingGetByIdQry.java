package com.digitforce.aip.dto.qry;


import com.digitforce.framework.api.dto.Query;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 通过id查询方案服务实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionServingGetByIdQry extends Query {
    private static final long serialVersionUID = -6813705211291548132L;
    private Long id;
}
