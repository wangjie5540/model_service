package com.digitforce.aip.dto.data;

import java.time.LocalDateTime;

/**
 * 方案实体定义类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:35
 */
public class SolutionTemplateDTO {
    private Long id;
    private String name;
    private String scene;
    private String description;
    private String pipelineId;
    private Integer browseCount;
    private Integer applyCount;
    private PipelineDataSource dataSource;
    private PipelineParameterDTO pipelineParameter;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
