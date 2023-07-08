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
 * @date 2023/7/8 15:59:33
 * @description
 */
@TableName("t_tag")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tag implements Serializable {
    @TableId(type = IdType.INPUT)
    private Long id;
    private String name;
    private Long userId;
    private Integer isDeleted;
}
