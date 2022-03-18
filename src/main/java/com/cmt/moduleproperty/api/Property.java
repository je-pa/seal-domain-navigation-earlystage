package com.cmt.moduleproperty.api;

import com.cmt.moduleproperty.api.PropertyType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Property {
    String name;
    String fullName;
    PropertyType type;
    String value;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
