package com.cmt.moduleproperty;

import com.cmt.moduleproperty.service.PropertyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ModulePropertyApplicationTests {

    @Autowired
    PropertyServiceImpl propertyServiceImpl;

    @Test
    void createPropertyTest() {
        Property property = propertyServiceImpl.createProperty("a", PropertyType.value);

        assertEquals("a", property.getName());
    }

    @Test
    void changeParentTest(){
        assertEquals(true, propertyServiceImpl.changeParent("aa","a"));
    }

    @Test
    void updatePropertyValue(){
        propertyServiceImpl.updatePropertyValue("a","hii");
    }

    @Test
    void findAllValueTypePropertiesTest(){
        List<Property> list = propertyServiceImpl.findAllValueTypeProperties();
        System.out.println(":::"+list.size());
    }
}
