package com.digitforce.aip.service.qry;

import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineQryFacade;
import com.digitforce.bdp.operatex.core.vo.TaskDefineVO;
import com.digitforce.framework.context.TenantContext;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class TaskDefineServiceTest {
    @Resource
    private TaskDefineQryFacade taskDefineQryFacade;

    @Before
    public void before() {
        TenantContext.init(10000);
    }

    @Test
    public void getTaskDefineById() {
        TaskDefineVO data = taskDefineQryFacade.getTaskDefineVOById(1541348442958238022L).getData();
        System.out.println(data);
    }
}