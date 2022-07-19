package com.digitforce.aip.dto.cmd;

import java.util.List;

/**
 * 方案批量修改修改实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 11:33
 */
public class SolutionTemplateBatchModifyCmd {
    private List<SolutionTemplateModifyCmd> solutionTemplateList;

    public List<SolutionTemplateModifyCmd> getSolutionTemplateList() {
        return solutionTemplateList;
    }

    public void setSolutionTemplateList(List<SolutionTemplateModifyCmd> solutionTemplateList) {
        this.solutionTemplateList = solutionTemplateList;
    }
}
