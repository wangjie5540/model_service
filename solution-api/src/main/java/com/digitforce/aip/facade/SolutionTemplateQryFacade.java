package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.SolutionTemplateDTO;
import com.digitforce.aip.dto.data.TemplateStatusListDTO;
import com.digitforce.aip.dto.qry.SolutionTemplateGetByIdQry;
import com.digitforce.aip.dto.qry.SolutionTemplatePageByQry;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * 方案服务查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution")
@Tag(name = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
public interface SolutionTemplateQryFacade {
    @PostMapping("/solution/solutionTemplate/getById")
    @Operation(summary = "通过id获取方案模板详情", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<SolutionTemplateDTO> getById(@RequestBody SolutionTemplateGetByIdQry solutionGetByIdQry);

    @PostMapping("/solution/solutionTemplate/pageBy")
    @Operation(summary = "分页查询方案模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<PageView<SolutionTemplateDTO>> pageBy(@RequestBody SolutionTemplatePageByQry solutionTemplatePageByQry);

    @PostMapping("/solution/solutionTemplate/listBy")
    @Operation(summary = "获取所有已上线模板", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<List<SolutionTemplateDTO>> listBy();

    @PostMapping("/solution/solutionTemplate/status/listBy")
    @Operation(summary = "模板状态列表", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<TemplateStatusListDTO> statusListBy();

    @PostMapping("/solution/solutionTemplate/getDataSourceTableMapping")
    @Operation(summary = "获取数据源表映射", tags = CommonConst.SWAGGER_TAG_SOLUTION_TEMPLATE_QRY)
    Result<Map<String, String>> getDataSourceTableMapping();
}
