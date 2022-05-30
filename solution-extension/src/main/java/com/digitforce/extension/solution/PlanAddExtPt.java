package com.digitforce.extension.solution;

import com.digitforce.framework.extension.ExtensionPoint;
import com.digitforce.aip.domain.Catalog;

/**
 * @author wangw
 * @since 2021/9/9
 */
public interface PlanAddExtPt extends ExtensionPoint {

    Catalog check(Catalog plan);
}
