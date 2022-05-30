package com.digitforce.aip.repository;

import com.digitforce.framework.repository.DefaultDBRepository;
import com.digitforce.aip.domain.Catalog;
import com.digitforce.aip.dto.event.CatalogNameModifiedEvent;
import com.digitforce.aip.mapper.CatalogMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CatalogRepositoryImpl extends DefaultDBRepository<Catalog> implements CatalogRepository {
    @Resource
    private CatalogMapper catalogMapper;
    @Resource
    private ApplicationEventPublisher publisher;


    @Override
    public CatalogMapper getMapper() {
        return catalogMapper;
    }

    @Override
    public boolean isExist(Catalog catalog) {
        //select id from t_catalog where ? limit 1
        return false;
    }

    @Override
    public List<Catalog> listLeafByCode(String code) {
        // select * from t_catalog where code like '${code}%'
        return null;
    }
}
