package com.digitforce.aip.service.qry;

import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.aip.repository.SolutionServingRepository;
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
public class SolutionServingQryServiceImpl extends DefaultService<SolutionServing>
    implements SolutionServingQryService {
    @Resource
    private SolutionServingRepository solutionServingRepository;
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    public SolutionServingRepository getRepository() {
        return solutionServingRepository;
    }
}
