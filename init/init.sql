CREATE
DATABASE aip_solution;
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
    UNIQUE KEY `name_uniq` (`name`) USING BTREE
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
    UNIQUE KEY `uniq_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='方案表';

SET
FOREIGN_KEY_CHECKS = 1;



/*
 Navicat Premium Data Transfer

 Source Server         : 算法平台-dev环境
 Source Server Type    : MySQL
 Source Server Version : 50738
 Source Host           : 172.21.32.143:3306
 Source Schema         : aip_model

 Target Server Type    : MySQL
 Target Server Version : 50738
 File Encoding         : 65001

 Date: 10/08/2022 18:46:53
*/
CREATE
DATABASE aip_model;
    USE
aip_model;

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for model
-- ----------------------------
CREATE TABLE `model`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`        int(11) DEFAULT NULL COMMENT '租户id',
    `template_id`      bigint(20) DEFAULT NULL COMMENT '模板id',
    `solution_id`      bigint(20) DEFAULT NULL COMMENT '方案id',
    `name`             varchar(255)      DEFAULT NULL COMMENT '名称',
    `cname`            varchar(255)      DEFAULT NULL COMMENT '展示名称',
    `resource_type`    varchar(64)       DEFAULT NULL COMMENT 'model or data',
    `size`             float             DEFAULT NULL COMMENT '单位MB',
    `metrics_list`     text COMMENT '模型指标',
    `package_id`       bigint(20) DEFAULT NULL COMMENT '模型包id',
    `task_id`          bigint(20) DEFAULT NULL COMMENT '任务id',
    `task_instance_id` bigint(20) DEFAULT NULL COMMENT '任务实例id',
    `ds_instance_id`   int(11) DEFAULT NULL COMMENT 'ds任务实例id',
    `pipeline_id`      varchar(255)      DEFAULT NULL COMMENT 'kubeflow的pipeline_id',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '逻辑删除',
    `create_time`      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for model_package
-- ----------------------------
CREATE TABLE `model_package`
(
    `id`               bigint(20) NOT NULL AUTO_INCREMENT COMMENT '模型包id',
    `template_id`      bigint(20) DEFAULT NULL COMMENT '方案模板id',
    `solution_id`      bigint(20) DEFAULT NULL COMMENT '方案id',
    `task_id`          varchar(255)  DEFAULT NULL COMMENT '任务id',
    `tenant_id`        int(11) DEFAULT NULL COMMENT '租户id',
    `task_instance_id` bigint(20) DEFAULT NULL COMMENT '任务实例id',
    `ds_instance_id`   int(11) DEFAULT NULL COMMENT 'ds任务实例id',
    `scene`            varchar(128)  DEFAULT NULL COMMENT '来源系统',
    `name`             varchar(255)  DEFAULT NULL COMMENT '模型包名称',
    `solution_name`    varchar(255)  DEFAULT NULL COMMENT '方案名称',
    `lifecycle`        int(11) DEFAULT '3' COMMENT '生存周期',
    `path`             varchar(2048) DEFAULT NULL COMMENT '模型数据路径',
    `pipeline_id`      varchar(255)  DEFAULT NULL COMMENT 'kubeflow的pipeline_id',
    `deleted`          tinyint(1) DEFAULT '0' COMMENT '删除标志',
    `create_time`      datetime      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`      datetime      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET
FOREIGN_KEY_CHECKS = 1;

