package com.digitforce.aip.facade;

import com.digitforce.framework.api.dto.Result;
import com.digitforce.aip.dto.data.CatalogDTO;
import com.digitforce.aip.dto.qry.CatalogListQry;
import com.digitforce.aip.dto.qry.CatalogPageQry;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("solution-service")
public interface CatalogQryFacade {

    /**
     * 基于类目编号获取类目信息
     *
     * @param catalogDto
     * @return
     */
    @PostMapping("/catalog/getById")
    Result<CatalogDTO> getById(@RequestBody CatalogDTO catalogDto);

    /**
     * 基于类目编号批量获取类目信息
     *
     * @param catalogIds
     * @return
     */
    @PostMapping("/catalog/getByIds")
    Result<List<CatalogDTO>> getByIds(@RequestBody List<Long> catalogIds);

    /**
     * 基于组合条件查询类目列表，单列的关系运算
     *
     * @param catalogListQry
     * @return
     */
    @PostMapping("/catalog/listBy")
    Result<List<CatalogDTO>> listBy(@RequestBody CatalogListQry catalogListQry);

    /**
     * 分页查询类目列表
     *
     * @param catalogPageQry
     * @return
     */
    @PostMapping("/catalog/pageBy")
    Result<List<CatalogDTO>> pageBy(@RequestBody CatalogPageQry catalogPageQry);


}
