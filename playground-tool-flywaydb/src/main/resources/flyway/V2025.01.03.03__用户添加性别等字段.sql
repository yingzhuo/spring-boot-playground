ALTER TABLE `t_user`
    ADD COLUMN `gender` CHAR(1)      NULL COMMENT '性别' AFTER `password`,
    ADD COLUMN `dob`    DATETIME     NULL COMMENT '出生日期' AFTER `gender`,
    ADD COLUMN `email`  VARCHAR(100) NULL COMMENT '电子邮件地址' AFTER `dob`;
