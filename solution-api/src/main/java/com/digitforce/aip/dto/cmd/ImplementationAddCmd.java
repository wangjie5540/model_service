package com.digitforce.aip.dto.cmd;

import com.digitforce.framework.api.dto.Command;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

/**
 * 新增方案实体类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:41
 */
@Schema(
        description = "新增方案实施实体类"
)
public class ImplementationAddCmd extends Command {

    private String solutionId;
    private String taskId;
    private String name;
    private String description;
    private String scene;
    private String schedule;
    private LocalDateTime expireAt;
    private String selection;

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

    public String getSolutionId() {
        return solutionId;
    }

    public void setSolutionId(String solutionId) {
        this.solutionId = solutionId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
