package com.Cra2iTeT.UniMatch.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Cra2iTeT
 * @description 好友
 * @since 2023/7/7 11:23:45
 */
@TableName("t_user_relation")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRelation {
    @TableId(type = IdType.INPUT)
    private Long id;
    private Long userOneId;
    private Long userTwoId;
    private Integer isDeleted;
    // TODO 建立UserId的联合索引
}
