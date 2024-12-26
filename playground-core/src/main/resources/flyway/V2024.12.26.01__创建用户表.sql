-- -----------------------------------------------------
-- Table `SpringBootPlayground`.`t_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpringBootPlayground`.`t_user` (
    `id` VARCHAR(45) NOT NULL COMMENT 'ID',
    `username` VARCHAR(45) NOT NULL COMMENT '用户名',
    `password` VARCHAR(45) NOT NULL COMMENT '口令',
    `enabled` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '启用标记',
    `authorities` VARCHAR(64) NOT NULL COMMENT '角色/权限',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    PRIMARY KEY (`id`),
    UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
    INDEX `idx_username_pwd` USING BTREE (`username`, `password`) VISIBLE)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `SpringBootPlayground`.`t_user_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SpringBootPlayground`.`t_user_address` (
    `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `zip_code` VARCHAR(10) NULL COMMENT '邮政编码',
    `detail` VARCHAR(200) NOT NULL COMMENT '具体地址',
    `user_id` VARCHAR(45) NOT NULL COMMENT '所属用户ID',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
     PRIMARY KEY (`id`))
    ENGINE = InnoDB
COMMENT = '用户地址';

