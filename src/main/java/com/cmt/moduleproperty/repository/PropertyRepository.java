package com.cmt.moduleproperty.repository;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.dto.PropertyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropertyRepository {
    int createProperty(PropertyDto propertyDto);
    Property selectProperty(PropertyDto propertyDto);//.....
    List<Property> findAllValueTypeProperties();
    List<Property> findChildProperties(String propertyFullName);
    int updatePropertyValue(String propertyFullName, String value);
}
