package com.github.yingzhuo.playground.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 应卓
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@TableName("t_user_address")
public class UserAddress implements Serializable {

    @TableId("id")
    private Long id;

    @TableField("zip_code")
    private String zipCode;

    @TableField("detail")
    private String detail;

    @TableField("user_id")
    private String userId;

    @TableField("create_time")
    private LocalDateTime createTime;

}
