package com.github.yingzhuo.playground.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 应卓
 */
@Data
@Entity
@Table(name = "t_user")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "authorities")
    private String authorities;

    @Column(name = "enabled")
    private Boolean enabled;

    @Column(name = "locked")
    private Boolean accountLocked;

    @Column(name = "expired_time")
    private LocalDateTime expiredTime;

}
