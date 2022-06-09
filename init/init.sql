CREATE
DATABASE `aip_solution` /*!40100 DEFAULT CHARACTER SET utf8mb4 */


-- aip_solution.solution definition
CREATE TABLE `solution`
(
    `id`                  bigint(20) NOT NULL AUTO_INCREMENT,
    `tenant_id`           varchar(128)          DEFAULT NULL,
    `type`                varchar(32)  NOT NULL,
    `cname`               varchar(128)          DEFAULT NULL,
    `scene`               varchar(128) NOT NULL,
    `pipeline_id`         varchar(128) NOT NULL,
    `pipeline_parameters` text,
    `description`         text,
    `status`              tinyint(1) NOT NULL DEFAULT '1',
    `deleted`             tinyint(1) DEFAULT NULL,
    `create_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1533365959992180739 DEFAULT CHARSET=utf8mb4 COMMENT='方案表';