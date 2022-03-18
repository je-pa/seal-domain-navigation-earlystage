package com.cmt.moduleproperty.core.dto;

import com.cmt.moduleproperty.api.PropertyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class PropertyDto {
    String name;
    String fullName;
    PropertyType type;
    String value;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
