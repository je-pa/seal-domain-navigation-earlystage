package com.cmt.moduleproperty.service;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.PropertyType;
import com.cmt.moduleproperty.dto.CreateSelectPropertyDto;
import com.cmt.moduleproperty.dto.PropertyDto;
import com.cmt.moduleproperty.mapper.PropertyMapper;
import com.cmt.moduleproperty.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired private PropertyRepository propertyRepository;

    @Override
    public Property saveProperty(String propertyFullName, String value) {
        if(doPropertyExist(propertyFullName)){
            return updatePropertyValue(propertyFullName, value).orElse(null);
        }
        if(createProperty(propertyFullName,PropertyType.VALUE) != null){
            return updatePropertyValue(propertyFullName, value).orElse(null);
        }
        return null;
    }

    @Override
    public List<Property> findChildProperties(String propertyFullName) {
        if(doPropertyExist(propertyFullName)){
            return propertyRepository.findChildProperties(propertyFullName);
        }
        return null;
    }

    @Override
    public Optional<Property> findProperty(String propertyFullName){
        CreateSelectPropertyDto createSelectPropertyDto = new CreateSelectPropertyDto();
        createSelectPropertyDto.setFullName(propertyFullName);
        PropertyDto propertyDto = propertyRepository.selectProperty(createSelectPropertyDto);
        Property property = PropertyMapper.INSTANCE.propertyDtoToProperty(propertyDto);
        return Optional.ofNullable(property);
    }

    @Override
    public String findValue(String propertyFullName) {
        return findProperty(propertyFullName).map(Property::getValue).orElse(null);
    }

    @Override
    public List<Property> findAllValueTypeProperties() {
//        return null;
//        List<Property> list = propertyRepository.findAllValueTypeProperties().stream().map(propertyDto-> PropertyMapper.INSTANCE.propertyDtoToProperty(propertyDto)).collect(Collectors.toList());
        return null;
    }

    @Override
    public boolean deleteProperty(String propertyFullName) {
        if(doPropertyExist(propertyFullName) && findChildProperties(propertyFullName).size()>0){
            return false;
        }
        return (propertyRepository.deleteProperty(propertyFullName) == 1);
    }

    public Property createProperty(String propertyFullName, PropertyType propertyType){
        if(doPropertyExist(propertyFullName)){
            return null;
        }
        CreateSelectPropertyDto createSelectPropertyDto = new CreateSelectPropertyDto();
        createSelectPropertyDto.setFullName(propertyFullName);

        String parentFullName = createSelectPropertyDto.getParentFullName();
        if(parentFullName != null){
            if(!doPropertyExist(parentFullName)){
                createProperty(parentFullName, PropertyType.GROUP); //
            }else if(findProperty(parentFullName).map(Property :: getType).orElse(null) != PropertyType.GROUP){
                return null;
            }
        }

        createSelectPropertyDto.setType(propertyType);
        propertyRepository.createProperty(createSelectPropertyDto);

        return PropertyMapper.INSTANCE.propertyDtoToProperty(propertyRepository.selectProperty(createSelectPropertyDto));
    }


    public Optional<Property> updatePropertyValue(String propertyFullName, String value){
        if(propertyRepository.updatePropertyValue(propertyFullName,value)==1){
            return findProperty(propertyFullName);
        }
        return Optional.empty();
    }

    public boolean doPropertyExist(String propertyFullName){
        return findProperty(propertyFullName).isPresent();
    }

}
