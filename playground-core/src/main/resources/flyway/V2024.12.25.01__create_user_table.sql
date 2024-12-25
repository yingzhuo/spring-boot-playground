-- 用户表
CREATE TABLE `t_user`
(
    `id`          bigint       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`    varchar(255) NOT NULL COMMENT '用户名',
    `password`    varchar(255) NOT NULL COMMENT '密码',
    `enabled`     bit(1) DEFAULT b'1' COMMENT '启用标记',
    `authorities` varchar(255) NOT NULL COMMENT '权限',
    PRIMARY KEY (`id`)
) COMMENT ='用户表';

-- 用户名字段索引
ALTER TABLE `t_user`
    ADD INDEX idx_username (`username`);

-- 初始化测试用户
INSERT INTO `t_user` (id, username, password, enabled, authorities)
VALUES (null, 'yingzhuo', '{noop}yingzhuo', 1, 'ROLE_ADMIN');
