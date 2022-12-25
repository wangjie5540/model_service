package com.digitforce.aip.facade;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class SolutionServingCmdTest {

    @Test
    public void add() {
//        SolutionServingAddCmd solutionServingAddCmd = new SolutionServingAddCmd();
//        solutionServingAddCmd.setSolutionId(123L);
//        solutionServingAddCmd.setServingType(ServingTypeEnum.ONLINE_SERVING);
//
//        List<TableSelection> selection = Lists.newArrayList();
//        TableSelection tableSelection = new TableSelection();
//        tableSelection.setTable("item");
//        Filter filter = new Filter();
//        filter.setRelation(RelationEnum.AND);
//        Condition condition = new Condition();
//        condition.setField("field1");
//        condition.setFunction(FunctionEnum.EQ);
//        condition.setDataType(DataTypeEnum.STRING);
//        condition.setParams(Lists.newArrayList("11"));
//        filter.setConditions(Lists.newArrayList(condition));
//        tableSelection.setFilter(filter);
//        tableSelection.setTable("aip.item");
//        selection.add(tableSelection);
//        solutionServingAddCmd.setSelection(selection);
//
//        SolutionServing solutionServing = ConvertTool.convert(solutionServingAddCmd, SolutionServing.class);
//        solutionServing.getSelection().forEach(ts -> ts.setFilterSql(ApplicationUtil.filterToSql(ts.getFilter())));
//        solutionServingCmdService.save(solutionServing);
    }
}