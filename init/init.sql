USE
aip_solution;
SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for solution
-- ----------------------------
CREATE TABLE `solution`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '方案主键id',
    `tenant_id`           int(11) DEFAULT NULL COMMENT '租户id',
    `template_id`         bigint(20) DEFAULT NULL COMMENT '模板主键id',
    `task_id`             bigint(20) DEFAULT NULL COMMENT '通过方案创建的任务id',
    `task_instance_id`    bigint(20) DEFAULT NULL COMMENT '任务实例id',
    `scene`               varchar(128)          DEFAULT NULL COMMENT '适用场景',
    `name`                varchar(255) NOT NULL COMMENT '方案名称',
    `selection`           longtext COMMENT '筛选范围等',
    `front_extra`         longtext COMMENT '前端透传字段',
    `schedule`            varchar(128)          DEFAULT NULL COMMENT '调度信息',
    `description`         longtext COMMENT '方案描述',
    `data_source`         longtext COMMENT '数据源描述信息',
    `pipeline_parameters` longtext COMMENT 'pipeline入参',
    `time_range`          int(11) DEFAULT NULL COMMENT '数据时间范围',
    `time_unit`           varchar(32)           DEFAULT NULL COMMENT '时间单位',
    `state`               varchar(128) NOT NULL DEFAULT 'NOT_EXECUTE' COMMENT '方案状态',
    `status`              varchar(32)  NOT NULL DEFAULT 'NOT_EXECUTE' COMMENT '方案状态',
    `create_user`         varchar(255)          DEFAULT NULL COMMENT '创建用户',
    `update_user`         varchar(255)          DEFAULT NULL COMMENT '更新用户',
    `deleted`             tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
    `create_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_tenant_name` (`tenant_id`, `name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for solution_serving
-- ----------------------------
CREATE TABLE `solution_serving`
(
    `id`           bigint(20) NOT NULL AUTO_INCREMENT COMMENT '方案服务id',
    `tenant_id`    int(11) DEFAULT NULL COMMENT '租户id',
    `template_id`  bigint(20) DEFAULT NULL COMMENT '方案模板id',
    `solution_id`  bigint(20) DEFAULT NULL COMMENT '方案id',
    `selection`    longtext COMMENT '筛选范围',
    `serving_type` varchar(128)      DEFAULT NULL COMMENT '方案服务类型',
    `create_user`  varchar(255)      DEFAULT NULL COMMENT '创建用户',
    `update_user`  varchar(255)      DEFAULT NULL COMMENT '更新用户',
    `deleted`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
    `create_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for solution_template
-- ----------------------------
CREATE TABLE `solution_template`
(
    `id`            bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模板主键id',
    `tenant_id`     bigint(20) DEFAULT NULL COMMENT '租户id',
    `name`          varchar(128)         DEFAULT NULL COMMENT '模板名称',
    `scene`         varchar(128)         DEFAULT NULL COMMENT '适用场景',
    `pipeline_id`   varchar(128)         DEFAULT NULL COMMENT 'kubeflow的pipeline_id',
    `pipeline_name` varchar(255)         DEFAULT NULL COMMENT 'kubeflow的pipeline名称',
    `description`   longtext COMMENT '模板描述',
    `browse_count`  int(11) NOT NULL DEFAULT '0' COMMENT '模板浏览次数',
    `apply_count`   int(11) NOT NULL DEFAULT '0' COMMENT '模板应用次数',
    `status`        varchar(32) NOT NULL DEFAULT 'DRAFT' COMMENT '状态',
    `create_user`   varchar(255)         DEFAULT NULL COMMENT '创建用户',
    `update_user`   varchar(255)         DEFAULT NULL COMMENT '更新用户',
    `deleted`       tinyint(1) NOT NULL DEFAULT '0' COMMENT '删除标志',
    `create_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uniq_tenant_name` (`tenant_id`, `name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='方案表';

SET
FOREIGN_KEY_CHECKS = 1;
