-- 添加方案运行id
ALTER TABLE aip_solution.solution
    ADD s_run_id BIGINT NULL COMMENT '方案运行id';
ALTER TABLE aip_solution.solution CHANGE s_run_id s_run_id BIGINT NULL COMMENT '方案运行id' AFTER a_run_id;
-- 添加模型版本
ALTER TABLE aip_solution.solution_run
    ADD version BIGINT DEFAULT 1 NOT NULL COMMENT '模型版本';
ALTER TABLE aip_solution.solution_run CHANGE version version BIGINT DEFAULT 1 NOT NULL COMMENT '模型版本' AFTER pipeline_params;
