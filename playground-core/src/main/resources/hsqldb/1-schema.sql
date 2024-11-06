CREATE SCHEMA playground;

-- 用户表
DROP TABLE t_user;
CREATE TABLE t_user
(
    id           INT NOT NULL,
    username     VARCHAR(45),
    password     VARCHAR(128),
    authorities  VARCHAR(256),
    enabled      BOOLEAN,
    locked       BOOLEAN,
    expired_time DATETIME,
    PRIMARY KEY (ID)
);
