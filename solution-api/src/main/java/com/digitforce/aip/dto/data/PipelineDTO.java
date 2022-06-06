package com.digitforce.aip.dto.data;

import java.util.List;

/**
 * kubeflow-pipeline实体定义类
 *
 * @author wangtonggui
 * @version 1.0.0
 * @since 2022/05/31 15:34
 */
public class PipelineDTO {
    private String id;
    private String createdAt;
    private String name;
    private List<PipelineParameterDTO> parameters;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PipelineParameterDTO> getParameters() {
        return parameters;
    }

    public void setParameters(List<PipelineParameterDTO> parameters) {
        this.parameters = parameters;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public static class DefaultVersion {
        private String id;
        private String createdAt;
        private String name;
        private String parameters;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParameters() {
            return parameters;
        }

        public void setParameters(String parameters) {
            this.parameters = parameters;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "DefaultVersion{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", name='" + name + '\'' +
                ", parameters='" + parameters + '\'' +
                ", description='" + description + '\'' +
                '}';
        }
    }

    @Override
    public String toString() {
        return "PipelineDTO{" +
            "id='" + id + '\'' +
            ", createdAt='" + createdAt + '\'' +
            ", name='" + name + '\'' +
            ", parameters=" + parameters +
            ", description='" + description + '\'' +
            '}';
    }
}
