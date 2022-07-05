package com.digitforce.aip.service.qry;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.repository.SolutionTemplateRepository;
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
public class SolutionTemplateQryServiceImpl extends DefaultService<SolutionTemplate> implements SolutionTemplateQryService {
    @Resource
    private SolutionTemplateRepository solutionRepository;

    @Override
    public SolutionTemplateRepository getRepository() {
        return solutionRepository;
    }
}
