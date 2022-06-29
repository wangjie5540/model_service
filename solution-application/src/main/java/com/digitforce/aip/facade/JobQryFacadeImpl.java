package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.JobDTO;
import com.digitforce.aip.dto.qry.JobGetByIdQry;
import com.digitforce.aip.dto.qry.JobListByImplementationQry;
import com.digitforce.framework.api.dto.PageQuery;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 任务实例查询接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class JobQryFacadeImpl implements JobQryFacade {
    @Override
    public Result<JobDTO> getById(JobGetByIdQry jobGetByIdQry) {
        return null;
    }

    @Override
    public Result<List<JobDTO>> listByImplementationId(PageQuery<JobListByImplementationQry> jobListByImplementationQry) {
        return null;
    }
}
