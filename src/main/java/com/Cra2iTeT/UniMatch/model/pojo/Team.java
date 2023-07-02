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
 * @since 2023/6/19 19:05
 */
@TableName("t_team")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Team implements Serializable {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String name;
    private String description;
    private Long captain;
    private Integer level;
    private Integer maxNum;
    private Integer status;
    private Integer isDeleted;
}
