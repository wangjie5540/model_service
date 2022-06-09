package com.digitforce.aip.service.qry;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.repository.SolutionRepository;
import com.digitforce.framework.operation.DefaultService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 方案查询服务接口实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 13:59
 */
@Service
public class SolutionQryServiceImpl extends DefaultService<Solution> implements SolutionQryService {
    @Resource
    private SolutionRepository solutionRepository;

    @Override
    public SolutionRepository getRepository() {
        return solutionRepository;
    }
}
