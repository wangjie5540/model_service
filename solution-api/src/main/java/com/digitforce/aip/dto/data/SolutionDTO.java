package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.SolutionStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Data
public class SolutionDTO {
    private Long id;
    private String name;
    private List<TableSelection> selection;
    private Object frontExtra;
    private String schedule;
    private Long templateId;
    private Long taskId;
    private String scene;
    private String description;
    private PipelineDataSource dataSource;
    private Integer timeRange;
    private ChronoUnit timeUnit;
    private SolutionStatusEnum status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
