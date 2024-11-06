package com.github.yingzhuo.playground.controller.data;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm implements Serializable {

    @NotEmpty(message = "用户名不可为空")
    private String username;

    @NotEmpty(message = "密码怎么是空的咧")
    private String password;

}
