package com.digitforce.aip.dto.cmd;

/**
 * 方案修改实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/06/06 11:33
 */
public class SolutionModifyCmd {
    private Long id;
    private String cname;
    private String pipelineId;
    private String type;
    private String scene;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }
}
