package com.github.yingzhuo.playground.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 应卓
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName("t_user")
public class User implements Serializable {

    @TableId
    private Long id;

    @TableField("username")
    private String username;

    @TableField("password")
    private String password;

    @TableField("authorities")
    private String authorities;

    @TableField("enabled")
    private Boolean enabled;

}
