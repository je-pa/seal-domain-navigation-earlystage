package com.cmt.moduleproperty.core.dto;

import com.cmt.moduleproperty.api.PropertyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreatePropertyDto {
    Long id;
    String fullName;
    PropertyType type;
    String value;

    public String getName(){
        return fullName.substring(fullName.lastIndexOf(".")+1);
    }

    public String getParentFullName(){
        int dotLastIndexOf = fullName.lastIndexOf(".");
        if(dotLastIndexOf == -1){
            return null;
        }
        return fullName.substring(0,dotLastIndexOf);
    }
}
