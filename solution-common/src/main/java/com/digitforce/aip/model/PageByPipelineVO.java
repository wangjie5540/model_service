package com.digitforce.aip.model;

import lombok.Data;

import java.util.List;

@Data
public class PageByPipelineVO {
    private List<Pipeline> pipelines;
    private Integer totalSize;
    private String nextPageToken;
}
