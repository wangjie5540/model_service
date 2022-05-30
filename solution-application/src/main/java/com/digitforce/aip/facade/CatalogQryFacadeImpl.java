package com.digitforce.aip.facade;

import com.digitforce.framework.api.dto.Result;
import com.digitforce.aip.dto.data.CatalogDTO;
import com.digitforce.aip.dto.qry.CatalogListQry;
import com.digitforce.aip.dto.qry.CatalogPageQry;
import com.digitforce.aip.service.qry.CatalogQryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.digitforce.framework.tool.ConvertTool;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@RestController
public class CatalogQryFacadeImpl implements CatalogQryFacade {

    @Resource
    private CatalogQryService catalogQryService;

    /**
     * 基于类目编号获取类目信息
     *
     * @param catalogDto
     * @return
     */
    @Override
    public Result<CatalogDTO> getById(@RequestBody CatalogDTO catalogDto) {
        CatalogDTO catalogDTO = ConvertTool.convert(catalogQryService.getById(catalogDto.getId()), CatalogDTO.class);
        return Result.success(catalogDTO);
    }

    /**
     * 基于类目编号批量获取类目信息
     *
     * @param catalogIds
     * @return
     */
    @Override
    public Result<List<CatalogDTO>> getByIds(@RequestBody List<Long> catalogIds) {
        List<CatalogDTO> catalogDTOList = ConvertTool.convert(catalogQryService.listByIds(catalogIds), CatalogDTO.class);
        return Result.success(catalogDTOList);
    }

    /**
     * 基于组合条件查询类目列表，单列的关系运算
     *
     * @param catalogListQry
     * @return
     */
    @Override
    public  Result<List<CatalogDTO>> listBy(@RequestBody CatalogListQry catalogListQry) {
        return Result.success(Collections.emptyList());
    }

    /**
     * 分页查询类目列表
     *
     * @param catalogPageQry
     * @return
     */
    @Override
    public Result<List<CatalogDTO>> pageBy(@RequestBody CatalogPageQry catalogPageQry) {
        return Result.success(Collections.emptyList());
    }


}
