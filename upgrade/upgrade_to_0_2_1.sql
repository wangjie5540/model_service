-- 添加方案运行id
ALTER TABLE aip_solution.solution
    ADD s_run_id BIGINT NULL COMMENT '方案运行id';
ALTER TABLE aip_solution.solution CHANGE s_run_id s_run_id BIGINT NULL COMMENT '方案运行id' AFTER a_run_id;
-- 添加模型版本
ALTER TABLE aip_solution.model_package
    ADD version BIGINT NULL COMMENT '模型版本';
ALTER TABLE aip_solution.model_package CHANGE version version BIGINT NULL COMMENT '模型版本' AFTER solution_id;

