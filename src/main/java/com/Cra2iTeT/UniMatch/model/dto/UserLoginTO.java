package com.Cra2iTeT.UniMatch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cra2iTeT
 * @since 2023/6/24 14:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginTO implements Serializable {
    private String account;
    private String password;
}
