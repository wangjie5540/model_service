package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ServingStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServingInstanceDTO {
    private Long id;
    private Long servingId;
    private ServingStatusEnum status;
    private LocalDateTime updateTime;
}
