package com.digitforce.extension.solution;


import com.digitforce.framework.api.extension.TenantCode;

import static com.digitforce.framework.api.extension.ExtScenario.DEFAULT_SCENARIO;
import static com.digitforce.framework.api.extension.ExtScenario.DOT;

/**
 * @author wangw
 * @since 2021/9/9
 */
public interface IbpExtCode extends TenantCode {

    String PLAN_ADD = DF + DOT + "PLAN_ADD" + DOT + DEFAULT_SCENARIO;

}
