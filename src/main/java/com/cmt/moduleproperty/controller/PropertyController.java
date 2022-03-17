package com.cmt.moduleproperty.controller;

import com.cmt.moduleproperty.Property;
import com.cmt.moduleproperty.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/property")
public class PropertyController {
    @Autowired private PropertyService propertyService;

    @GetMapping("/savedelete")
    public String save(Property property, Model model){
        model.addAttribute("properties", propertyService.findAllValueTypeProperties());
        return "save_delete_property";
    }

    @GetMapping("/findChildProperties")
    public String findChildProperties(Property property, Model model){
        model.addAttribute("properties", propertyService.findAllValueTypeProperties());
        model.addAttribute("ChildProperties", propertyService.findChildProperties(property.getFullName()));
        return "save_delete_property";
    }

    @GetMapping("/findProperty")
    public String findProperty(Property property, Model model){
        model.addAttribute("properties", propertyService.findAllValueTypeProperties());
        model.addAttribute("findProperty", propertyService.findProperty(property.getFullName()).orElse(null));
        return "save_delete_property";
    }

    @GetMapping("/findValue")
    public String findValue(Property property, Model model){
        model.addAttribute("properties", propertyService.findAllValueTypeProperties());
        model.addAttribute("value", propertyService.findValue(property.getFullName()));
        return "save_delete_property";
    }

    @PostMapping("/save")
    public String saveProperty(Property property){
        propertyService.saveProperty(property.getFullName(),property.getValue());
        return "redirect:/property/savedelete";
    }

    @DeleteMapping("/delete")
    public String deleteProperty(Property property){
        propertyService.deleteProperty(property.getFullName());
        return "redirect:/property/savedelete";
    }
}
