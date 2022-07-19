package com.digitforce.aip.dto.data;

import com.digitforce.aip.GlobalConstant;
import com.digitforce.aip.enums.TemplateStatusEnum;

import java.time.LocalDateTime;

/**
 * 方案模板实体定义类
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
    private String pipelineName;
    private Integer browseCount;
    private Integer applyCount;
    private PipelineDataSource dataSource;
    // TODO 后续将迭代调度机制
    private String schedule = GlobalConstant.DEFAULT_CRON;
    private PipelineParameterDTO pipelineParameter;
    private TemplateStatusEnum status;
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

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public TemplateStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TemplateStatusEnum status) {
        this.status = status;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }
}
