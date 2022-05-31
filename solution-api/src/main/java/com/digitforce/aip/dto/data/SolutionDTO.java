package com.digitforce.aip.dto.data;

import java.util.List;

/**
 * 方案实体定义类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
public class SolutionDTO {
    private long id;
    private String type;
    private String cname;
    private String scene;
    private String description;
    private String pipelineId;
    private String pipelineDescription;
    private List<PipelineParameterDTO> pipelineParameters;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getPipelineDescription() {
        return pipelineDescription;
    }

    public void setPipelineDescription(String pipelineDescription) {
        this.pipelineDescription = pipelineDescription;
    }

    public List<PipelineParameterDTO> getPipelineParameters() {
        return pipelineParameters;
    }

    public void setPipelineParameters(List<PipelineParameterDTO> pipelineParameters) {
        this.pipelineParameters = pipelineParameters;
    }
}
