package com.digitforce.aip.dto.data;

/**
 * 方案实体定义类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
public class SolutionTemplateDTO {
    private long id;
    private String cname;
    private String scene;
    private String description;
    private String pipelineId;
    private Integer browseCount;
    private Integer applyCount;
    private PipelineDataSource dataSource;
    private PipelineParameterDTO pipelineParameter;

    public PipelineDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(PipelineDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Integer getBrowseCount() {
        return browseCount;
    }

    public void setBrowseCount(Integer browseCount) {
        this.browseCount = browseCount;
    }

    public Integer getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(Integer applyCount) {
        this.applyCount = applyCount;
    }

    public PipelineParameterDTO getPipelineParameter() {
        return pipelineParameter;
    }

    public void setPipelineParameter(PipelineParameterDTO pipelineParameter) {
        this.pipelineParameter = pipelineParameter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
