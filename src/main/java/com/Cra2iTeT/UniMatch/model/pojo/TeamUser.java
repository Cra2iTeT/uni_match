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
 * @since 2023/6/19 19:07
 */
@TableName("t_Team_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamUser implements Serializable {
    @TableId(type = IdType.INPUT)
    private Long id;
    private Long teamId;
    private Long userId;
    private Integer isDeleted;
}
