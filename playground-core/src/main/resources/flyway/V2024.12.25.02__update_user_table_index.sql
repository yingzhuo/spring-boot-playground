-- 删除旧索引
ALTER TABLE t_user DROP INDEX idx_username;

-- 添加联合索引
ALTER TABLE t_user ADD INDEX idx_username_password(`username`, `password`);
