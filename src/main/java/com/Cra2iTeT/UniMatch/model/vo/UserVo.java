package com.Cra2iTeT.UniMatch.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {
    private Long id;
    private String account;
    private String username;
    private String avatarUrl;
    private String email;
    private String phone;
    private Integer gender;
    private Integer userRole;
    private String tags;
    private String token;
}
