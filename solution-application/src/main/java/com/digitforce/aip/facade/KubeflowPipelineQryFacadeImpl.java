package com.digitforce.aip.facade;

import com.digitforce.aip.config.KubeflowProperties;
import com.digitforce.aip.dto.data.Pipeline;
import com.digitforce.aip.dto.qry.PipelineGetByIdQry;
import com.digitforce.aip.entity.PipelinePage;
import com.digitforce.aip.utils.KubeflowHelper;
import com.digitforce.framework.api.dto.Result;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * kubeflow-pipeline查询接口类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 17:08
 */
@RestController
public class KubeflowPipelineQryFacadeImpl implements KubeflowPipelineQryFacade {
    @Resource
    private KubeflowProperties kubeflowProperties;

    @Override
    public Result<Pipeline> getById(PipelineGetByIdQry pipelineGetByIdQry) {
        Pipeline pipeline = KubeflowHelper.getPipelineDetail(kubeflowProperties.getHost(),
                kubeflowProperties.getPort(), pipelineGetByIdQry.getId());
        return Result.success(pipeline);
    }

    @Override
    public Result<List<Pipeline>> listBy() {
        // TODO kubeflow使用pageToken进行分页，后续需要进行兼容性开发
        PipelinePage pipelinePage = KubeflowHelper.pageByPipeline(kubeflowProperties.getHost(),
                kubeflowProperties.getPort(), 1000);
        return Result.success(pipelinePage.getPipelines());
    }
}
