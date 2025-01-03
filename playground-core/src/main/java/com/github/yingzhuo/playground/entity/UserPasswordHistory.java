package com.github.yingzhuo.playground.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@TableName("t_user_pwd_his")
public class UserPasswordHistory implements Serializable {

    @TableId("id")
    private String id;

    @TableField("hashed_pwd")
    private String hashedPassword;

    @TableField("user_id")
    private String userId;

    @TableField("create_time")
    private LocalDateTime createTime;

}
