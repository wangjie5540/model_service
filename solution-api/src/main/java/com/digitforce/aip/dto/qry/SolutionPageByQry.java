package com.digitforce.aip.dto.qry;


import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 方案分页查询实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SolutionPageByQry extends PageQuery<SolutionDTO> {
    @Deprecated
    private String nameLike;
}
