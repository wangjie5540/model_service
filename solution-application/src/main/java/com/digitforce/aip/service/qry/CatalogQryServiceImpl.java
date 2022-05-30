package com.digitforce.aip.service.qry;

import com.digitforce.framework.operation.DefaultService;
import com.digitforce.aip.domain.Catalog;
import com.digitforce.aip.repository.CatalogRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CatalogQryServiceImpl extends DefaultService<Catalog> implements CatalogQryService {
    @Resource
    private CatalogRepository catalogRepository;

    @Override
    public CatalogRepository getRepository() {
        return catalogRepository;
    }
}
