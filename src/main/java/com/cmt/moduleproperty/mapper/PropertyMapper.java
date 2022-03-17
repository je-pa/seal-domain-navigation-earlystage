package com.cmt.moduleproperty.mapper;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.dto.PropertyDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

//    @Mapping(source = "name", target = "name")
    Property propertyDtoToProperty(PropertyDto propertyDto);
}
