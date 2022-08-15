package com.digitforce.aip.service.cmd;

import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.repository.SolutionServingRepository;
import com.digitforce.framework.operation.DefaultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 方案服务接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:59
 */
@Service
public class SolutionServingCmdServiceImpl extends DefaultService<SolutionServing> implements SolutionServingCmdService {
    @Resource
    private SolutionServingRepository solutionServingRepository;

    @Override
    public SolutionServingRepository getRepository() {
        return solutionServingRepository;
    }
}
