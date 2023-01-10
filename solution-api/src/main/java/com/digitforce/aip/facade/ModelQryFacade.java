package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.ModelDTO;
import com.digitforce.aip.dto.data.ModelPackageDTO;
import com.digitforce.aip.dto.qry.ModelPackagePageByQry;
import com.digitforce.aip.dto.qry.ModelPageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 模型查询类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_MODEL_QRY, description = "modelQry")
public interface ModelQryFacade {
    @PostMapping("/solution/modelPackage/pageBy")
    @Operation(summary = "分页查询模型包", tags = CommonConst.SWAGGER_TAG_MODEL_QRY)
    Result<PageView<ModelPackageDTO>> modelPackagePageBy(@RequestBody ModelPackagePageByQry modelPackagePageByQry);

    @PostMapping("/solution/model/pageBy")
    @Operation(summary = "分页查询模型详情", tags = CommonConst.SWAGGER_TAG_MODEL_QRY)
    Result<PageView<ModelDTO>> pageBy(@RequestBody ModelPageByQry modelPageByQry);
}
