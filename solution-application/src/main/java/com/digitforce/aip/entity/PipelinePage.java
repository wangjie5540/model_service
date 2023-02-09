package com.digitforce.aip.entity;

import com.digitforce.aip.dto.data.Pipeline;
import lombok.Data;

import java.util.List;

@Data
public class PipelinePage {
    private List<Pipeline> pipelines;
    private Integer totalSize;
    private String nextPageToken;
}
