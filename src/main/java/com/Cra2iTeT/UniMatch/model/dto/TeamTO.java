package com.Cra2iTeT.UniMatch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author Cra2iTeT
 * @date 2023/7/8 16:50:24
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamTO implements Serializable {
    private String name;
    private List<Long> userIds;
    private String description;
}
