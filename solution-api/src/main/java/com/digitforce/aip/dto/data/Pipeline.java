package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class Pipeline {
    private String id;
    private String name;
    private String description;
    private List<Parameter> parameters;
    private DefaultVersion defaultVersion;
}
