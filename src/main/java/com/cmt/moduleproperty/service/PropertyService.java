package com.cmt.moduleproperty.service;

import com.cmt.moduleproperty.Property;

import java.util.List;
import java.util.Optional;

/**
 * @author JiEun Park
 * @since 1.0
 */
public interface PropertyService {
    Property saveProperty(String propertyFullName, String value);
    List<Property> findChildProperties(String propertyFullName);
    Optional<Property> findProperty(String propertyFullName); //optional
    String findValue(String propertyFullName);
    List<Property> findAllValueTypeProperties();
    boolean deleteProperty(String propertyFullName);
}
