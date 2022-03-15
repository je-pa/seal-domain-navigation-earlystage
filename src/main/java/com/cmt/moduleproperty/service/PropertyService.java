package com.cmt.moduleproperty.service;

import com.cmt.moduleproperty.Property;

import java.util.List;

public interface PropertyService {
    Property saveProperty(String propertyFullName, String value);
    List<Property> findChildProperties(String propertyFullName);
    String findValue(String propertyFullName);
    List<Property> findAllValueTypeProperties();
    boolean deleteProperty(String propertyFullName);
}
