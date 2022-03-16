package com.cmt.moduleproperty;

import com.cmt.moduleproperty.service.PropertyServiceImpl;
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
    void savePropertyTest(){
        Property property = propertyServiceImpl.saveProperty("b.c.d.g.h","abcb");
//        assertEquals("ta.es.ag.st",property.fullName);
//        assertEquals("test11112",property.value);
    }

    @Test
    void findChildPropertiesTest(){
        List<Property> propertyList = propertyServiceImpl.findChildProperties("a");
    }

    @Test
    void findValueTest(){
        String value = propertyServiceImpl.findValue("a.b.c.d.g.h");
//        String value2 = propertyServiceImpl.findValue("a.b.c.d.g.h");
//        String value3 = propertyServiceImpl.findValue("ta.b.c");
    }

    @Test
    void findAllValueTypePropertiesTest(){
        List<Property> list = propertyServiceImpl.findAllValueTypeProperties();
    }

    @Test
    void deletePropertyTest(){
        assertEquals(true, propertyServiceImpl.deleteProperty("b.c.d.g.h"));
    }

    @Test
    void createPropertyTest() {
        Property property = propertyServiceImpl.createProperty("main.sub.su.s.s1", PropertyType.VALUE);

//        assertEquals("a", property.getName());
    }

    @Test
    void selectPropertyByPropertyFullNameTest(){
        assertEquals(null, propertyServiceImpl.findProperty("a"));
    }

    @Test
    void updatePropertyValueTest(){
        propertyServiceImpl.updatePropertyValue("a","hii");
    }

    @Test
    void doPropertyExist(){
        assertEquals(true , propertyServiceImpl.doPropertyExist("ta.b.c"));
    }

//    @Test
//    void changeParentTest(){
//        assertEquals(true, propertyServiceImpl.changeParent("d","a.b.c"));
//    }

}
