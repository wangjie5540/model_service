package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.ModelPackageDTO;
import com.digitforce.aip.dto.qry.ModelPackagePageByQry;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import org.junit.Test;

import javax.annotation.Resource;

public class ModelQryFacadeImplTest extends BaseTest {
    @Resource
    private ModelQryFacade modelQryFacade;

    @Test
    public void modelPackagePageBy() {
        ModelPackagePageByQry modelPackagePageByQry = new ModelPackagePageByQry();
        modelPackagePageByQry.setPageNum(1);
        modelPackagePageByQry.setPageSize(10);
        Result<PageView<ModelPackageDTO>> pageViewResult = modelQryFacade.modelPackagePageBy(modelPackagePageByQry);
        System.out.println(pageViewResult);
    }
}