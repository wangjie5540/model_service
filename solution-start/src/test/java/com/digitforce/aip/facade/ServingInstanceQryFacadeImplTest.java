package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.ServingInstanceDTO;
import com.digitforce.aip.dto.qry.ServingInstanceGetByIdQry;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.framework.api.dto.Result;
import org.junit.Test;

import javax.annotation.Resource;


public class ServingInstanceQryFacadeImplTest extends BaseTest {
    @Resource
    private ServingInstanceQryFacade servingInstanceQryFacade;

    @Test
    public void getById() {
        ServingInstanceGetByIdQry servingInstanceGetByIdQry = new ServingInstanceGetByIdQry();
        servingInstanceGetByIdQry.setId(1643488934744276993L);
        Result<ServingInstanceDTO> result = servingInstanceQryFacade.getById(servingInstanceGetByIdQry);
        System.out.println(result);
    }
}