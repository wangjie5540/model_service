package com.digitforce.aip.consts;

import lombok.Data;

import java.util.Objects;

/**
 * 提供一个类目编码工具用于生成编码和反向解析类目编号，类目编号是指父级编号
 */
@Data
public class CatalogCode {

    public static final String SEPARATOR = "/";
    private Long firstId;
    private Long secondId;
    private Long thirdId;
    private Long forthId;
    private String code;

    public CatalogCode() {
    }

    public CatalogCode(Long firstId, Long secondId, Long thirdId, Long forthId) {
        this.firstId = firstId;
        this.secondId = secondId;
        this.thirdId = thirdId;
        this.forthId = forthId;
        if (Objects.nonNull(firstId) && Objects.nonNull(secondId) && Objects.nonNull(thirdId) && Objects.nonNull(forthId)) {
            code = firstId + SEPARATOR + secondId + SEPARATOR + thirdId + SEPARATOR + forthId + SEPARATOR;
        } else if (Objects.nonNull(firstId) && Objects.nonNull(secondId) && Objects.nonNull(thirdId)) {
            code = firstId + SEPARATOR + secondId + SEPARATOR + thirdId + SEPARATOR;
        } else if (Objects.nonNull(firstId) && Objects.nonNull(secondId)) {
            code = firstId + SEPARATOR + secondId + SEPARATOR;
        } else if (Objects.nonNull(firstId)) {
            code = firstId + SEPARATOR;
        }
    }

    public CatalogCode(String code) {
        this.code = code;
        String[] catalogIds = code.split(SEPARATOR);
        if (catalogIds.length == 1) {
            firstId = Long.parseLong(catalogIds[0]);
        } else if (catalogIds.length == 2) {
            firstId = Long.parseLong(catalogIds[0]);
            secondId = Long.parseLong(catalogIds[1]);
        } else if (catalogIds.length == 3) {
            firstId = Long.parseLong(catalogIds[0]);
            secondId = Long.parseLong(catalogIds[1]);
            thirdId = Long.parseLong(catalogIds[2]);
        } else if (catalogIds.length == 4) {
            firstId = Long.parseLong(catalogIds[0]);
            secondId = Long.parseLong(catalogIds[1]);
            thirdId = Long.parseLong(catalogIds[2]);
            forthId = Long.parseLong(catalogIds[3]);
        }
    }

    /**
     * 基于类目的父级编号构建类目编码
     *
     * @param firstId  一级父类目编号
     * @param secondId 二级父类目编号
     * @param thirdId  三级父类目编号
     * @param forthId  四级父类目编号
     * @return
     */
    public static CatalogCode of(Long firstId, Long secondId, Long thirdId, Long forthId) {
        return new CatalogCode(firstId, secondId, thirdId, forthId);
    }

    /**
     * 基于类目的父级编号构建类目编码
     *
     * @param firstId  一级父类目编号
     * @param secondId 二级父类目编号
     * @param thirdId  三级父类目编号
     * @return
     */
    public static CatalogCode of(Long firstId, Long secondId, Long thirdId) {
        return new CatalogCode(firstId, secondId, thirdId, null);
    }

    /**
     * @param firstId  一级父类目编号
     * @param secondId 二级父类目编号
     * @return
     */
    public static CatalogCode of(Long firstId, Long secondId) {
        return new CatalogCode(firstId, secondId, null, null);
    }

    /**
     * @param firstId 一级父类目编号
     * @return
     */
    public static CatalogCode of(Long firstId) {
        return new CatalogCode(firstId, null, null, null);
    }

    public static CatalogCode of(String code) {
        return new CatalogCode(code);
    }

}
