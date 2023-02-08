ALTER TABLE aip_solution.solution
    ADD s_run_id BIGINT NULL COMMENT '方案运行id';
ALTER TABLE aip_solution.solution CHANGE s_run_id s_run_id BIGINT NULL COMMENT '方案运行id' AFTER a_run_id;
