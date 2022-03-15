package com.cmt.moduleproperty.service;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.PropertyType;
import com.cmt.moduleproperty.dto.PropertyDto;
import com.cmt.moduleproperty.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService{
    @Autowired private PropertyRepository propertyRepository;

    @Override
    public Property saveProperty(String propertyFullName, String value) {
        if(selectPropertyByPropertyFullName(propertyFullName) != null){
            return updatePropertyValue(propertyFullName,value);
        }

        String[] names = propertyFullName.split(".");
        for(int i=0 ; i<names.length ; i++){

        }
        return null;
    }

    @Override
    public List<Property> findChildProperties(String propertyFullName) {
        if(selectPropertyByPropertyFullName(propertyFullName) != null){

        }
        return null;
    }

    @Override
    public String findValue(String propertyFullName) {
        return selectPropertyByPropertyFullName(propertyFullName).getValue();
    }

    @Override
    public List<Property> findAllValueTypeProperties() {
        return propertyRepository.findAllValueTypeProperties();
    }

    @Override
    public boolean deleteProperty(String propertyFullName) {
        return false;
    }

    public boolean changeParent(String propertyFullName, String parentGroupPropertyFullName){
        return false;
    }//하위 풀네임...

    public Property createProperty(String propertyName, PropertyType propertyType){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setName(propertyName);
        propertyDto.setType(propertyType);
        propertyRepository.createProperty(propertyDto);

        return propertyRepository.selectProperty(propertyDto);
    }

    public Property updatePropertyValue(String propertyFullName, String value){
        if(propertyRepository.updatePropertyValue(propertyFullName,value)==1){
            return selectPropertyByPropertyFullName(propertyFullName);
        }
        return null;
    }

    public Property selectPropertyByPropertyFullName(String propertyFullName){
        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setFullName(propertyFullName);
        return propertyRepository.selectProperty(propertyDto);
    }
}
