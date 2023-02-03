package com.digitforce.aip.utils;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.digitforce.aip.consts.CommonConst;
import com.digitforce.aip.dto.data.Condition;
import com.digitforce.aip.dto.data.Filter;
import com.digitforce.aip.enums.FunctionEnum;
import com.digitforce.aip.enums.RelationEnum;
import com.digitforce.aip.query.QueryWrapper;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.function.Consumer;

@UtilityClass
public class ApplicationUtil {
    public String filterToSql(Filter filter) {
        QueryWrapper<Object> queryWrapper = new QueryWrapper<>();
        reverse(filter, queryWrapper);
        return queryWrapper.getTargetSql();
    }

    public static void reverse(Filter filter, QueryWrapper<Object> queryWrapper) {
        if (CollectionUtil.isNotEmpty(filter.getFilters())) {
            filter.getFilters().forEach(f -> reverse(f, queryWrapper));
        }
        Consumer<QueryWrapper<Object>> consumer = c -> {
            for (Condition condition : filter.getConditions()) {
                String field = condition.getField();
                List<Object> params = condition.getParams();
                FunctionEnum function = condition.getFunction();
                switch (function) {
                    case EQ:
                        c.eq(field, params.get(0));
                        break;
                    case NE:
                        c.ne(field, params.get(0));
                        break;
                    case BETWEEN:
                        c.between(field, params.get(0), params.get(1));
                        break;
                }
            }
        };
        if (CollectionUtil.isNotEmpty(filter.getConditions())) {
            if (filter.getRelation() == RelationEnum.AND) {
                queryWrapper.and(consumer);
            } else {
                queryWrapper.or(consumer);
            }
        }
    }

    public static String generateServingResultFileName(Integer tenantId, Long servingInstanceId) {
        return StrUtil.format("{}-{}.csv", tenantId.toString(), servingInstanceId.toString());
    }

    public static String generateServingResultUrl(Integer tenantId, Long servingInstanceId) {
        String resultFileName = StrUtil.format("{}-{}.csv", tenantId.toString(), servingInstanceId.toString());
        return StrUtil.format("{}/{}", CommonConst.COS_BASE_URL, resultFileName);
    }
}
