package com.digitforce.aip.domain;

import com.digitforce.aip.consts.CatalogCode;
import com.digitforce.aip.consts.CommonStatus;
import com.digitforce.aip.dto.event.CatalogNameModifiedEvent;
import com.digitforce.aip.po.CatalogPO;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.List;
import java.util.Objects;

@Data
@TableName("cata_log")
public class Catalog extends CatalogPO {

    private boolean nameModified;
    private List<Catalog> children;

    /**
     * 添加类目时准备相关数据
     *
     * @param parentCatalog
     */
    public void preAdd(Catalog parentCatalog) {
        setLevel(parentCatalog.getLevel() + 1);
        generateCode(parentCatalog);
    }

    /**
     * 修改时准备数据
     *
     * @param oldCatalog
     */
    public void preModify(Catalog oldCatalog) {
        if (Objects.nonNull(getName())) {
            if (!getName().equals(oldCatalog.getName())) {
                nameModified = true;
            } else {
                setName(null);
            }
        }
    }

    /**
     * 生成类目编码，
     * 一级类目格式“/”，
     * 二级类目格式为“/firstId/”，
     * 三级类目格式为“/firstId/secondId/”
     * 四级类目格式为“/firstId/secondId/thirdId/”
     *
     * @param parentCatalog
     * @return
     */
    public String generateCode(Catalog parentCatalog) {
        String code;
        if (Objects.isNull(parentCatalog)) {
            code = CatalogCode.SEPARATOR;
        } else {
            code = parentCatalog.getCode() + getPid() + CatalogCode.SEPARATOR;
        }
        setCode(code);
        return code;
    }

    /**
     * 层级合适
     *
     * @param parentCatalog
     * @return
     */
    public boolean mayExchange(Catalog parentCatalog) {
        boolean mayExchange = getLevel() > parentCatalog.getLevel();
        mayExchange = mayExchange && parentCatalog.getLevel() + (4 - getLevel()) <= 4;
        return mayExchange;
    }

    /**
     * 是否能够修改类目
     *
     * @return
     */
    public boolean mayModify() {
        return CommonStatus.OFF.isEqual(getStatus());

    }

    public CatalogNameModifiedEvent catalogNameModifyEvent() {

        return null;
    }
}
