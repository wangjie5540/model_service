package com.digitforce.aip.entity;

import lombok.Data;
import lombok.Getter;

/**
 * kubeflow run实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/10 14:20
 */
@Data
public class Run {
    private String id;
    private String name;
    private String status;


    @Getter
    public enum StatusEnum {
        SUCCEEDED("Succeeded"),
        FAILED("Failed"),
        RUNNING("Running");
        private final String value;

        StatusEnum(String value) {
            this.value = value;
        }
    }
}