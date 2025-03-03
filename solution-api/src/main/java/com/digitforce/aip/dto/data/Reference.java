package com.digitforce.aip.dto.data;

import lombok.Data;

/**
 * kubeflow请求响应reference
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/10 16:57
 */
@Data
public class Reference {
    private Key key = new Key();
    private String relationship;


    @Data
    public static class Key {
        private String id;
        private String type;
    }
}
