package com.cmt.moduleproperty.api;

import java.util.List;
import java.util.Optional;

/**
 * @author JiEun Park
 * @since 1.0
 */
public interface PropertyService {
    Property saveProperty(String propertyFullName, String value);
//    Property createProperty(String propertyFullName, String value) throws DuplicatedPropertyFullNameException;
    ///////중복키를 넣었을 때
    ///Exception 던지기
    List<Property> findChildProperties(String propertyFullName);
    Optional<Property> findProperty(String propertyFullName); //optional
    String findValue(String propertyFullName);
    List<Property> findAllValueTypeProperties();
    boolean deleteProperty(String propertyFullName);
}
