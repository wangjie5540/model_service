package com.digitforce.aip.dto.cmd;

/**
 * 方案修改实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 11:33
 */
public class SolutionTemplateModifyCmd {
    private Long id;
    private String name;
    private String scene;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
