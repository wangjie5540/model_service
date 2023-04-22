package com.digitforce.aip.facade;

import com.digitforce.aip.dto.data.Roc;
import com.digitforce.aip.dto.data.SolutionRunDTO;
import com.digitforce.aip.dto.qry.SolutionRunPageByQry;
import com.digitforce.aip.test.BaseTest;
import com.digitforce.framework.api.dto.PageView;
import com.digitforce.framework.api.dto.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;

import javax.annotation.Resource;

public class SolutionRunQryFacadeImplTest extends BaseTest {
    @Resource
    private SolutionRunQryFacade solutionRunQryFacade;
    @Resource
    private ObjectMapper objectMapper;

    @Test
    public void pageBy() {
        SolutionRunPageByQry solutionRunPageByQry = new SolutionRunPageByQry();
        SolutionRunDTO solutionRunDTO = new SolutionRunDTO();
        solutionRunDTO.setId(1632636388446232578L);
        solutionRunPageByQry.setClause(solutionRunDTO);
        Result<PageView<SolutionRunDTO>> pageViewResult = solutionRunQryFacade.pageBy(solutionRunPageByQry);
        pageViewResult.getData().getList().forEach(System.out::println);
    }

    /**
     * roc反序列化
     */
    @SneakyThrows
    @Test
    public void rocDeserialization() {
        Roc roc = objectMapper.readValue("{\"x\":[0.0,0.05211726384364821,0.06188925081433225,0.09120521172638436,0" +
                        ".15309446254071662,0.19218241042345277,0.2182410423452769,0.28338762214983715,0" +
                        ".2964169381107492,0" +
                        ".3517915309446254,0.42671009771986973,0.43973941368078173,0.48859934853420195,0" +
                        ".5309446254071661,0" +
                        ".8338762214983714,0.8664495114006515,0.9087947882736156,1.0],\"y\":[0.0,0.04804804804804805," +
                        "0" +
                        ".06906906906906907,0.10810810810810811,0.14714714714714713,0.2072072072072072,0" +
                        ".24924924924924924,0" +
                        ".2852852852852853,0.3033033033033033,0.3483483483483483,0.40540540540540543,0" +
                        ".42342342342342343,0" +
                        ".4744744744744745,0.5015015015015015,0.7987987987987988,0.8348348348348348,0" +
                        ".8918918918918919,1.0]}"
                , Roc.class);
        System.out.println(roc);
    }
}