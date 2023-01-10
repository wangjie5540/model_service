package com.digitforce.aip.dto.data;

import com.digitforce.aip.enums.ServingInstanceStatusEnum;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ServingInstanceDTO {
    private Long id;
    private Long servingId;
    private ServingInstanceStatusEnum status;
    private LocalDateTime updateTime;
}
