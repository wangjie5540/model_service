package com.digitforce.aip.repository;

import com.digitforce.aip.domain.Catalog;
import com.digitforce.framework.operation.CrudOperation;

import java.util.List;

public interface CatalogRepository extends CrudOperation<Catalog> {

    boolean isExist(Catalog catalog);

    List<Catalog> listLeafByCode(String code);

}
