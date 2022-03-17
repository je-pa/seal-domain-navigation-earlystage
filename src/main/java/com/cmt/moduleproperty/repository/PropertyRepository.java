package com.cmt.moduleproperty.repository;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.dto.ChangeParentPropertyDto;
import com.cmt.moduleproperty.dto.CreateSelectPropertyDto;
import com.cmt.moduleproperty.dto.PropertyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropertyRepository {
    int createProperty(CreateSelectPropertyDto propertyDto);
    PropertyDto selectProperty(CreateSelectPropertyDto createSelectPropertyDto);//.....
    List<PropertyDto> findAllValueTypeProperties();
    List<PropertyDto> findChildProperties(String propertyFullName);
    int updatePropertyValue(String propertyFullName, String value);
    int deleteProperty(String propertyFullName);

    int changeParent(ChangeParentPropertyDto changeParentPropertyDto);
}
