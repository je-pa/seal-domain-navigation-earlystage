package com.cmt.moduleproperty.dto;

import com.cmt.moduleproperty.PropertyType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateSelectPropertyDto {
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
