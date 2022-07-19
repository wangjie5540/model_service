package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionDTO;
import com.digitforce.aip.dto.qry.SolutionGetByIdQry;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.context.TenantContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionQryFacadeImplTest {
    @Resource
    private SolutionQryFacade solutionQryFacade;

    @Test
    public void getById() {
        TenantContext.init(10000);
        SolutionGetByIdQry solutionGetByIdQry = new SolutionGetByIdQry();
        solutionGetByIdQry.setId(1548849120332660737L);
        Result<SolutionDTO> byId = solutionQryFacade.getById(solutionGetByIdQry);
        System.out.println(byId);
    }
}