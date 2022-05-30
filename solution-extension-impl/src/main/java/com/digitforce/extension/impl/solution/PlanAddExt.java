package com.digitforce.extension.impl.solution;

import com.digitforce.extension.solution.IbpExtCode;
import com.digitforce.extension.solution.PlanAddExtPt;
import com.digitforce.framework.extension.annotation.Extension;
import com.digitforce.aip.domain.Catalog;

/**
 * @author wangw
 * @since 2021/9/9
 */
@Extension(name = "添加计划", code = IbpExtCode.PLAN_ADD)
public class PlanAddExt implements PlanAddExtPt {
    @Override
    public Catalog check(Catalog plan) {
        return null;
    }
}
