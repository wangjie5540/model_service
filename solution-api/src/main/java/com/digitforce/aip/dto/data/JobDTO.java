package com.digitforce.aip.dto.data;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class JobDTO {
    private Long id;
    private String name;
    private Long taskId;
    private Long implementationId;
    private String taskStatus;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalTime duration;
}
