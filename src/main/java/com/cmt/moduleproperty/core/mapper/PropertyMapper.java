package com.cmt.moduleproperty.core.mapper;

import com.cmt.moduleproperty.api.Property;
import com.cmt.moduleproperty.core.dto.PropertyDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    Property propertyDtoToProperty(PropertyDto propertyDto);
}
