package com.cmt.moduleproperty.core.service;

import com.cmt.moduleproperty.api.NotFoundPropertyException;
import com.cmt.moduleproperty.api.Property;
import com.cmt.moduleproperty.api.PropertyType;
import com.cmt.moduleproperty.api.PropertyService;
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
            return propertyRepository.findChildProperties(propertyFullName)
                    .stream()
                    .map(propertyDto-> PropertyMapper.INSTANCE.propertyDtoToProperty(propertyDto))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public Optional<Property> findProperty(String propertyFullName){
//        PropertyDto propertyDto = propertyRepository.selectProperty(createSelectPropertyDto);
//        Property property = PropertyMapper.INSTANCE.propertyDtoToProperty(propertyDto);
        return Optional.ofNullable(
                PropertyMapper.INSTANCE.propertyDtoToProperty(
                        propertyRepository.selectPropertyByFullName(propertyFullName)
                )
            );
    }

    @Override
    public String findValue(String propertyFullName) {
        Optional<Property> propertyOptional = findProperty(propertyFullName);
        if(propertyOptional.isEmpty()){
            throw new NotFoundPropertyException();
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
        if(doPropertyExist(propertyFullName) && findChildProperties(propertyFullName).size()>0){
            return false;
        }
        return (propertyRepository.deleteProperty(propertyFullName) == 1);
    }

    Property createProperty(String propertyFullName, PropertyType propertyType){
        if(doPropertyExist(propertyFullName)){
            return null;
        }
        CreatePropertyDto createSelectPropertyDto = new CreatePropertyDto();
        createSelectPropertyDto.setFullName(propertyFullName);

        String parentFullName = createSelectPropertyDto.getParentFullName();
        if(parentFullName != null){
            if(!doPropertyExist(parentFullName)){
                createProperty(parentFullName, PropertyType.GROUP); //
            }else if(findProperty(parentFullName)
                    .map(Property :: getType)
                    .orElse(null) != PropertyType.GROUP
            ){
                return null;
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

    boolean doPropertyExist(String propertyFullName){
        return findProperty(propertyFullName).isPresent();
    }

}
