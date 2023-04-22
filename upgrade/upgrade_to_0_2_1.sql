-- 添加方案运行id
ALTER TABLE aip_solution.solution
    ADD s_run_id BIGINT NULL COMMENT '方案运行id';
ALTER TABLE aip_solution.solution CHANGE s_run_id s_run_id BIGINT NULL COMMENT '方案运行id' AFTER a_run_id;
-- 添加模型版本
ALTER TABLE aip_solution.solution_run
    ADD version BIGINT DEFAULT 1 NOT NULL COMMENT '模型版本';
ALTER TABLE aip_solution.solution_run CHANGE version version BIGINT DEFAULT 1 NOT NULL COMMENT '模型版本' AFTER pipeline_params;
-- 添加ale值
ALTER TABLE aip_solution.serving_instance
    ADD ale TEXT NULL COMMENT 'ale值';
ALTER TABLE aip_solution.serving_instance CHANGE ale ale MEDIUMTEXT NULL COMMENT 'ale值' AFTER `result`;
-- 在预测实例表增加模型运行id字段
ALTER TABLE aip_solution.serving_instance
    ADD run_id BIGINT NULL COMMENT '模型运行id';
ALTER TABLE aip_solution.serving_instance CHANGE run_id run_id BIGINT NULL COMMENT '模型运行id' AFTER serving_id;
-- 在预测视力表添加模型版本
ALTER TABLE aip_solution.serving_instance
    ADD model_version BIGINT NULL COMMENT '模型版本';
ALTER TABLE aip_solution.serving_instance CHANGE model_version model_version BIGINT NULL COMMENT '模型版本' AFTER p_run_name;
-- 增加已发布模型数量字段
ALTER TABLE aip_solution.scene
    ADD online_model_count INT DEFAULT 0 NULL COMMENT '已发布模型数量';
ALTER TABLE aip_solution.scene CHANGE online_model_count online_model_count INT DEFAULT 0 NULL COMMENT '已发布模型数量' AFTER solution_count;
