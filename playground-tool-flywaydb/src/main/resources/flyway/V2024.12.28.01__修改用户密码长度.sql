-- 修改用户密码长度
ALTER TABLE `SpringBootPlayground`.`t_user`
    CHANGE COLUMN `password` `password` VARCHAR(100) NOT NULL COMMENT '口令' ;