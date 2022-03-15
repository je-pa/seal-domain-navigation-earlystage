package com.cmt.moduleproperty.dto;

import com.cmt.moduleproperty.PropertyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PropertyDto {
    Long id;
    String name;
    String fullName;
    PropertyType type;
    Long parentId;
    String value;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
