package com.digitforce.aip.test;

import com.digitforce.bdp.operatex.core.api.taskDefine.TaskDefineQryFacade;
import com.digitforce.bdp.operatex.core.vo.TaskDefineVO;
import com.digitforce.framework.api.dto.Result;
import com.digitforce.framework.context.TenantContext;
import com.digitforce.manager.api.dict.client.DictEntryQryFacade;
import com.digitforce.manager.api.dict.dto.DictEntryDTO;
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
public class DictTest {
    @Resource
    private DictEntryQryFacade dictEntryQryFacade;
    @Resource
    private TaskDefineQryFacade taskDefineQryFacade;

    @Before
    public void before() {
        TenantContext.init(10000);
    }

    @Test
    public void get() {
        DictEntryDTO dictEntryDTO = new DictEntryDTO();
        dictEntryDTO.setId(43L);
        Result<DictEntryDTO> dictEntryDTOResult = dictEntryQryFacade.getById(dictEntryDTO);
        System.out.println(dictEntryDTOResult);
    }

    @Test
    public void task() {
        Result<TaskDefineVO> taskDefineVOById = taskDefineQryFacade.getTaskDefineVOById(1541348442958238012L);
        System.out.println(taskDefineVOById);
    }
}
