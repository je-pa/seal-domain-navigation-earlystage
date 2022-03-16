package com.cmt.moduleproperty.service;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.PropertyType;
import com.cmt.moduleproperty.dto.ChangeParentPropertyDto;
import com.cmt.moduleproperty.dto.PropertyDto;
import com.cmt.moduleproperty.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired private PropertyRepository propertyRepository;

    @Override
    public Property saveProperty(String propertyFullName, String value) {
        if(doPropertyExist(propertyFullName)){
            return updatePropertyValue(propertyFullName, value);
        }
        if(createProperty(propertyFullName,PropertyType.VALUE) != null){
            return updatePropertyValue(propertyFullName, value);
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
    public Property findProperty(String propertyFullName){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setFullName(propertyFullName);
        return propertyRepository.selectProperty(propertyDto);
    }

    @Override
    public String findValue(String propertyFullName) {
        return findProperty(propertyFullName).getValue();
    }

    @Override
    public List<Property> findAllValueTypeProperties() {
        return propertyRepository.findAllValueTypeProperties();
    }

    @Override
    public boolean deleteProperty(String propertyFullName) {
        if(!doPropertyExist(propertyFullName) && findChildProperties(propertyFullName).size()>0){
            return false;
        }
        return (propertyRepository.deleteProperty(propertyFullName) == 1);
    }

    public Property createProperty(String propertyFullName, PropertyType propertyType){
        if(doPropertyExist(propertyFullName)){
            return null;
        }
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setFullName(propertyFullName);

        String parentFullName = propertyDto.getParentFullName();
        if(parentFullName != null){
            if(!doPropertyExist(propertyFullName)){
                createProperty(parentFullName, PropertyType.GROUP); //
            }else if(findProperty(parentFullName).getType() != PropertyType.GROUP){
                return null;
            }
        }

        propertyDto.setType(propertyType);
        propertyRepository.createProperty(propertyDto);

        return propertyRepository.selectProperty(propertyDto);
    }


    public Property updatePropertyValue(String propertyFullName, String value){
        if(propertyRepository.updatePropertyValue(propertyFullName,value)==1){
            return findProperty(propertyFullName);
        }
        return null;
    }

    public boolean doPropertyExist(String propertyFullName){
        if(findProperty(propertyFullName) != null){
            return true;
        }
        return false;
    }

}
