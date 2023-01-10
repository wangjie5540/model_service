package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.RunStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SolutionRunDTO {
    @Schema(description = "主键id")
    private Long id;
    private RunStatusEnum status;
    @Schema(description = "更新人")
    private LocalDateTime createTime;
}
