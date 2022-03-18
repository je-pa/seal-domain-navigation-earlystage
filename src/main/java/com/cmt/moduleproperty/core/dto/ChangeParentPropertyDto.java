package com.cmt.moduleproperty.core.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeParentPropertyDto {
    String propertyFullName;
    String parentGroupPropertyFullName;

    public String getNewPropertyFullName(){
        return parentGroupPropertyFullName+"."+getName();
    }

    public String getName(){
        return propertyFullName.substring(propertyFullName.lastIndexOf(".")+1);
    }
}
