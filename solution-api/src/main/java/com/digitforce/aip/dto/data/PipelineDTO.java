package com.digitforce.aip.dto.data;


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
    private PipelineParameterDTO pipelineParameterDTO;
    private String description;
    private DefaultVersion defaultVersion;

    public DefaultVersion getDefaultVersion() {
        return defaultVersion;
    }

    public void setDefaultVersion(DefaultVersion defaultVersion) {
        this.defaultVersion = defaultVersion;
    }

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

    public PipelineParameterDTO getPipelineParameterDTO() {
        return pipelineParameterDTO;
    }

    public void setPipelineParameterDTO(PipelineParameterDTO pipelineParameterDTO) {
        this.pipelineParameterDTO = pipelineParameterDTO;
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
        private PipelineParameterDTO pipelineParameter;
        private String description;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public PipelineParameterDTO getPipelineParameter() {
            return pipelineParameter;
        }

        public void setPipelineParameter(PipelineParameterDTO pipelineParameter) {
            this.pipelineParameter = pipelineParameter;
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
                    ", parameters='" + pipelineParameter + '\'' +
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
                ", parameters=" + pipelineParameterDTO +
                ", description='" + description + '\'' +
                '}';
    }
}
