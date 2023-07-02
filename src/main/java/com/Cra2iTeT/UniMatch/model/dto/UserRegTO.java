package com.Cra2iTeT.UniMatch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Cra2iTeT
 * @since 2023/6/19 19:28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegTO {
    private String email;
    private String phone;
    private String password;
    private String code;
}
