package com.digitforce.aip.dto.data;

import lombok.Data;

/**
 * kubeflow pipeline的入参类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/12/21 16:32
 */
@Data
public class PipelineParams {
    private Object trainingSampleParams;
    private Object modelHyperParams;
}
