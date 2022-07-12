package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import com.digitforce.aip.dto.qry.SolutionTemplateGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 方案服务查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
@RequestMapping(path = "/solutionTemplate")
public interface SolutionTemplateQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过id获取方案详情", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<SolutionTemplateDTO> getById(@RequestBody SolutionTemplateGetByIdQry solutionGetByIdQry);

    @PostMapping("/pageBy")
    @Operation(summary = "分页查询", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<PageView<SolutionTemplateDTO>> pageBy(@RequestBody SolutionTemplatePageByQry solutionTemplatePageByQry);
}
