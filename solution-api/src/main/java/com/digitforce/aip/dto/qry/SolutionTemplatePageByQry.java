package com.digitforce.aip.dto.qry;


import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.Data;

/**
 * 分页查询请求实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 14:39
 */
@Data
public class SolutionTemplatePageByQry extends PageQuery<SolutionTemplateDTO> {
    private String nameLike;
}
