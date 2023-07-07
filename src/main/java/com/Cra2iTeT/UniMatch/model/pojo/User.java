package com.Cra2iTeT.UniMatch.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Cra2iTeT
 * @since 2023/6/12 11:41
 */
@TableName("t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String account;
    private String username;
    private String password;
    private String avatarUrl;
    private String email;
    private String phone;
    private Integer gender;
    private Integer userRole;
    private String tags;
    private Integer isDeleted;

    // TODO 建立account索引
}
