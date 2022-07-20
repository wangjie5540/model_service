package com.digitforce.aip.listener;

import com.digitforce.aip.enums.SolutionStatusEnum;
import lombok.Data;

@Data
public class MessageDTO {
    private Long taskId;
    private Long taskInstanceId;
    private SolutionStatusEnum status;
}
