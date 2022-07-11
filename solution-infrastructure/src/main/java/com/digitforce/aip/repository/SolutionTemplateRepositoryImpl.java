package com.digitforce.aip.repository;

import com.digitforce.aip.domain.SolutionTemplate;
import com.digitforce.aip.mapper.SolutionTemplateMapper;
import com.digitforce.framework.repository.DefaultDBRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 方案持久化存储实现类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/05 15:54
 */
@Repository
public class SolutionTemplateRepositoryImpl extends DefaultDBRepository<SolutionTemplate> implements SolutionTemplateRepository {
    @Resource
    private SolutionTemplateMapper solutionTemplateMapper;

    @Override
    public boolean isExist(SolutionTemplate solution) {
        solution = solutionTemplateMapper.selectById(solution.getId());
        return solution != null;
    }

    @Override
    public SolutionTemplateMapper getMapper() {
        return solutionTemplateMapper;
    }
}
