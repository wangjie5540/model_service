package com.digitforce.aip.dto.qry;


import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;

/**
 * 方案分页查询实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
@Data
public class SolutionPageByQry extends PageQuery<SolutionDTO> {
    private String nameLike;
}
