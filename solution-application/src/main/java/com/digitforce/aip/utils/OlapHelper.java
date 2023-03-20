package com.digitforce.aip.utils;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class OlapHelper {
    /**
     * 获取的得分表名
     *
     * @param solutionId 方案ID
     * @return 得分表名
     */
    public static String getScoreTableName(Long solutionId) {
        return StrUtil.format("aip.score_{}", solutionId.toString());
    }
}
