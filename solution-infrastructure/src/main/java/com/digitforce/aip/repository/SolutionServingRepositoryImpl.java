package com.digitforce.aip.repository;

import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.mapper.SolutionServingMapper;
import com.digitforce.framework.repository.DefaultDBRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class SolutionServingRepositoryImpl extends DefaultDBRepository<SolutionServing> implements SolutionServingRepository {
    @Resource
    private SolutionServingMapper solutionServingMapper;
    @Resource
    private ApplicationEventPublisher publisher;


    @Override
    public SolutionServingMapper getMapper() {
        return solutionServingMapper;
    }

    @Override
    public boolean isExist(SolutionServing solutionServing) {
        return false;
    }
}
