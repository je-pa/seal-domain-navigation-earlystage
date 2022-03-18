package com.cmt.moduleproperty.core.repository;

import com.cmt.moduleproperty.core.dto.CreatePropertyDto;
import com.cmt.moduleproperty.core.dto.PropertyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PropertyRepository {
    int createProperty(CreatePropertyDto propertyDto);
    PropertyDto selectPropertyById(Long id);//.....
    PropertyDto selectPropertyByFullName(String fullName);
    List<PropertyDto> findAllValueTypeProperties();
    List<PropertyDto> findChildProperties(String propertyFullName);
    int updatePropertyValue(String propertyFullName, String value);
    int deleteProperty(String propertyFullName);

//    int changeParent(ChangeParentPropertyDto changeParentPropertyDto);
}
