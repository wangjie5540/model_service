package com.digitforce.aip.dto.data;

import lombok.Data;

import java.util.List;

@Data
public class DefaultVersion {
    private String id;
    private String name;
    private List<Parameter> parameters;
    private List<Reference> resourceReferences;
}
