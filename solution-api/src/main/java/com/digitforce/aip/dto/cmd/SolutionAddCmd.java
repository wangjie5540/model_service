package com.digitforce.aip.dto.cmd;

import com.digitforce.aip.dto.data.PipelineDataSource;
import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "新增方案实体类"
)
public class SolutionAddCmd extends Command {
    private Long templateId;
    @Parameter(required = true)
    private String name;
    @Parameter(required = true)
    private String scene;
    private String description;
    private String schedule;
    private LocalDateTime expireAt;
    private Integer timeRange;
    private ChronoUnit timeUnit;
    private Object selection;
    private Boolean needExecute = false;
    private String pipelineId;
    private String pipelineName;
    private PipelineDataSource dataSource;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Long templateId) {
        this.templateId = templateId;
    }

    public void setSelection(Object selection) {
        this.selection = selection;
    }

    public Integer getTimeRange() {
        return timeRange;
    }

    public void setTimeRange(Integer timeRange) {
        this.timeRange = timeRange;
    }

    public ChronoUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(ChronoUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Object getSelection() {
        return selection;
    }

    public Boolean getNeedExecute() {
        return needExecute;
    }

    public void setNeedExecute(Boolean needExecute) {
        this.needExecute = needExecute;
    }

    public PipelineDataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(PipelineDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getPipelineId() {
        return pipelineId;
    }

    public void setPipelineId(String pipelineId) {
        this.pipelineId = pipelineId;
    }

    public String getPipelineName() {
        return pipelineName;
    }

    public void setPipelineName(String pipelineName) {
        this.pipelineName = pipelineName;
    }
}
