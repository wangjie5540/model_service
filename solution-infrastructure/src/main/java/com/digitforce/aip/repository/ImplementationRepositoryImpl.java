package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Implementation;
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
public class ImplementationRepositoryImpl extends DefaultDBRepository<Implementation> implements ImplementationRepository {
    @Resource
    private ImplementationMapper implementationMapper;

    @Override
    public ImplementationMapper getMapper() {
        return implementationMapper;
    }

    @Override
    public boolean isExist(Implementation implementation) {
        return false;
    }
}
