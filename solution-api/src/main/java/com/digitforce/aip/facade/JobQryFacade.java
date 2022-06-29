package com.digitforce.aip.facade;

import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.JobDTO;
import com.digitforce.aip.dto.qry.JobGetByIdQry;
import com.digitforce.aip.dto.qry.JobListByImplementationQry;
import com.digitforce.framework.api.dto.PageQuery;
import com.digitforce.framework.api.dto.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 任务实例查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 */
@FeignClient("solution-service")
@Tag(name = CommonConst.SWAGGER_TAG_JOB_QRY)
@RequestMapping(path = "/job")
public interface JobQryFacade {
    @PostMapping("/getById")
    @Operation(summary = "通过id获取任务详情", tags = CommonConst.SWAGGER_TAG_JOB_QRY)
    Result<JobDTO> getById(@RequestBody JobGetByIdQry jobGetByIdQry);

    @PostMapping("/pageBy")
    @Operation(summary = "获取任务分页", tags = CommonConst.SWAGGER_TAG_JOB_QRY)
    Result<List<JobDTO>> listByImplementationId(@RequestBody PageQuery<JobListByImplementationQry> jobListByImplementationQry);
}
