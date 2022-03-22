package com.cmt.moduleproperty.core.service;

import com.cmt.moduleproperty.api.*;
import com.cmt.moduleproperty.core.dto.CreatePropertyDto;
import com.cmt.moduleproperty.core.mapper.PropertyMapper;
import com.cmt.moduleproperty.core.repository.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property saveProperty(String propertyFullName, String value) {
        if(findProperty(propertyFullName).isEmpty()){
            createProperty(propertyFullName,PropertyType.VALUE);
        }
        updatePropertyValue(propertyFullName, value);

        return findProperty(propertyFullName).get();
    }

    @Override
    public List<Property> findChildProperties(String propertyFullName) {
        if(findProperty(propertyFullName).isEmpty()){
            throw new PropertyNotFoundException();
        }
        return propertyRepository.findChildProperties(propertyFullName)
                .stream()
                .map(propertyDto-> PropertyMapper.INSTANCE.propertyDtoToProperty(propertyDto))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Property> findProperty(String propertyFullName){
        return Optional.ofNullable(
                PropertyMapper.INSTANCE.propertyDtoToProperty(
                        propertyRepository.selectPropertyByFullName(propertyFullName)
                )
            );
    }

    @Override
    public String findValue(String propertyFullName) {
        if(findProperty(propertyFullName).isEmpty()){
            throw new PropertyNotFoundException();
        }
        return findProperty(propertyFullName).map(Property::getValue).orElse(null);
    }

    @Override
    public List<Property> findAllValueTypeProperties() {
        List<Property> list = propertyRepository.findAllValueTypeProperties()
                .stream()
                .map(propertyDto-> PropertyMapper.INSTANCE.propertyDtoToProperty(propertyDto))
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public boolean deleteProperty(String propertyFullName) {
        if(findProperty(propertyFullName).isPresent() && findChildProperties(propertyFullName).size()>0){
            return false;
        }
        return (propertyRepository.deleteProperty(propertyFullName) == 1);
    }

    Property createProperty(String propertyFullName, PropertyType propertyType){
        if(findProperty(propertyFullName).isPresent()){
            return null;
        }
        CreatePropertyDto createSelectPropertyDto = new CreatePropertyDto();
        createSelectPropertyDto.setFullName(propertyFullName);

        String parentFullName = createSelectPropertyDto.getParentFullName();
        if(parentFullName != null){
            if(findProperty(parentFullName).isEmpty()){
                createProperty(parentFullName, PropertyType.GROUP);
            }else if(findProperty(parentFullName)
                    .map(Property :: getType)
                    .orElse(null) != PropertyType.GROUP
            ){
                throw new BadRequestException(parentFullName+"'s PropertyType is not "+ PropertyType.GROUP);
            }
        }

        createSelectPropertyDto.setType(propertyType);
        propertyRepository.createProperty(createSelectPropertyDto);

        return PropertyMapper.INSTANCE
                .propertyDtoToProperty(propertyRepository.selectPropertyById(createSelectPropertyDto.getId()));
    }


    Optional<Property> updatePropertyValue(String propertyFullName, String value){
        if(propertyRepository.updatePropertyValue(propertyFullName,value)==1){
            return findProperty(propertyFullName);
        }
        return Optional.empty();
    }
}
