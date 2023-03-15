package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.SolutionRunDTO;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import org.junit.Test;

import javax.annotation.Resource;

public class SolutionRunQryFacadeImplTest extends BaseTest {
    @Resource
    private SolutionRunQryFacade solutionRunQryFacade;

    @Test
    public void pageBy() {
        SolutionRunPageByQry solutionRunPageByQry = new SolutionRunPageByQry();
        SolutionRunDTO solutionRunDTO = new SolutionRunDTO();
        solutionRunDTO.setId(1632636388446232578L);
        solutionRunPageByQry.setClause(solutionRunDTO);
        Result<PageView<SolutionRunDTO>> pageViewResult = solutionRunQryFacade.pageBy(solutionRunPageByQry);
        pageViewResult.getData().getList().forEach(System.out::println);
    }
}