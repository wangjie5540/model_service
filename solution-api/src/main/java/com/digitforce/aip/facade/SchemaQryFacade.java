package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SchemaDTO;
import com.digitforce.aip.dto.qry.SchemaGetByTableQry;
import com.digitforce.aip.dto.qry.SchemaListTableQry;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 表schema信息查询
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SCENE_QRY)
@RequestMapping(path = "/schema")
public interface SchemaQryFacade {
    @PostMapping("/listTable")
    @Operation(summary = "获取table列表", tags = CommonConst.SWAGGER_TAG_SCENE_QRY)
    Result<List<String>> listTable(@RequestBody SchemaListTableQry schemaListTableQry);

    @PostMapping("/getByTable")
    @Operation(summary = "表schema信息查询", tags = CommonConst.SWAGGER_TAG_SCENE_QRY)
    Result<SchemaDTO> getByTable(@RequestBody SchemaGetByTableQry schemaGetByTableQry);
}
