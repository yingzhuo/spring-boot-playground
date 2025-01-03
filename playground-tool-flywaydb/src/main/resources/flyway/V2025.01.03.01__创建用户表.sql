-- 用户表
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          varchar(45)  NOT NULL COMMENT 'ID',
    `username`    varchar(45)  NOT NULL COMMENT '用户名',
    `password`    varchar(100) NOT NULL COMMENT '口令',
    `enabled`     tinyint(1)   NOT NULL DEFAULT '1' COMMENT '启用标记',
    `authorities` varchar(64)  NOT NULL COMMENT '角色/权限',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_UNIQUE` (`username`),
    KEY `idx_username_pwd` (`username`, `password`)
);

-- 用户地址表
DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE `t_user_address`
(
    `id`          varchar(45) COMMENT 'ID',
    `user_id`     varchar(45)  NOT NULL COMMENT '所属用户ID',
    `zip_code`    varchar(10)           DEFAULT NULL COMMENT '邮政编码',
    `detail`      varchar(200) NOT NULL COMMENT '具体地址',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_userid` (`user_id`)
);
