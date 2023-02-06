package com.digitforce.aip.utils;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.digitforce.framework.api.dto.OrderDesc;
import com.digitforce.framework.api.dto.PageQuery;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class PageUtil {
    public static <T> Page<T> page(PageQuery<?> pageQuery) {
        if (Objects.isNull(pageQuery)) {
            return new Page<>();
        }
        Page<T> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize(), pageQuery.isCountable());
        if (Objects.isNull(pageQuery.getOrderDescs())) {
            return page;
        }
        for (OrderDesc orderDesc : pageQuery.getOrderDescs()) {
            if (orderDesc.isAsc()) {
                page.addOrder(OrderItem.asc(orderDesc.getCol()));
            } else {
                page.addOrder(OrderItem.desc(orderDesc.getCol()));
            }
        }
        return page;
    }
}
