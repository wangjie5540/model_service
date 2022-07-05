package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Solution;
import com.digitforce.aip.mapper.ImplementationMapper;
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
public class SolutionRepositoryImpl extends DefaultDBRepository<Solution> implements SolutionRepository {
    @Resource
    private ImplementationMapper implementationMapper;

    @Override
    public ImplementationMapper getMapper() {
        return implementationMapper;
    }

    @Override
    public boolean isExist(Solution implementation) {
        return false;
    }
}
