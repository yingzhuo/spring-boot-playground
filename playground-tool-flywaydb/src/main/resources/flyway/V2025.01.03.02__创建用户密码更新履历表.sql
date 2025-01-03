-- 用户更新履历表
DROP TABLE IF EXISTS `t_user_pwd_his`;
CREATE TABLE `t_user_pwd_his`
(
    `id`          VARCHAR(45)  NOT NULL COMMENT 'ID',
    `hashed_pwd`  VARCHAR(100) NOT NULL COMMENT '密码',
    `user_id`     VARCHAR(45)  NOT NULL COMMENT '所属用户ID',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_userid_createtime` (`user_id` ASC, `create_time` DESC)
) COMMENT = '用户密码更新履历';