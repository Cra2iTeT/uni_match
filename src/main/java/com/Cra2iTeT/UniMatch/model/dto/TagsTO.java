package com.Cra2iTeT.UniMatch.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Cra2iTeT
 * @since 2023/6/24 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagsTO implements Serializable {
    private Long userId;
    private String tags;
}
