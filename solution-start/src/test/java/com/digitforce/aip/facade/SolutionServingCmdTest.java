package com.digitforce.aip.facade;

import com.digitforce.aip.domain.SolutionServing;
import com.digitforce.aip.dto.cmd.SolutionServingAddCmd;
import com.digitforce.aip.dto.data.Condition;
import com.digitforce.aip.dto.data.Filter;
import com.digitforce.aip.dto.data.TableSelection;
import com.digitforce.aip.enums.DataTypeEnum;
import com.digitforce.aip.enums.FunctionEnum;
import com.digitforce.aip.enums.RelationEnum;
import com.digitforce.aip.enums.ServingTypeEnum;
import com.digitforce.aip.service.cmd.SolutionServingCmdService;
import com.digitforce.aip.utils.ApplicationUtil;
import com.digitforce.framework.tool.ConvertTool;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionServingCmdTest {
    @Resource
    private SolutionServingCmdService solutionServingCmdService;

    @Test
    public void add() {
        SolutionServingAddCmd solutionServingAddCmd = new SolutionServingAddCmd();
        solutionServingAddCmd.setSolutionId(123L);
        solutionServingAddCmd.setServingType(ServingTypeEnum.ONLINE_SERVING);

        List<TableSelection> selection = Lists.newArrayList();
        TableSelection tableSelection = new TableSelection();
        tableSelection.setTable("item");
        Filter filter = new Filter();
        filter.setRelation(RelationEnum.AND);
        Condition condition = new Condition();
        condition.setField("field1");
        condition.setFunction(FunctionEnum.EQ);
        condition.setDataType(DataTypeEnum.STRING);
        condition.setParams(Lists.newArrayList("11"));
        filter.setConditions(Lists.newArrayList(condition));
        tableSelection.setFilter(filter);
        tableSelection.setTable("aip.item");
        selection.add(tableSelection);
        solutionServingAddCmd.setSelection(selection);

        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
        solutionServing.getSelection().forEach(ts -> ts.setFilterSql(ApplicationUtil.filterToSql(ts.getFilter())));
        solutionServingCmdService.save(solutionServing);
    }
}