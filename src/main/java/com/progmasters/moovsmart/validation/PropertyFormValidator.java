package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.PropertyForm;
import com.progmasters.moovsmart.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PropertyFormValidator implements Validator {

    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyFormValidator(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PropertyForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PropertyForm property = (PropertyForm) o;
        if (property.getName() == null || property.getName().equals("")) {
            errors.rejectValue("name", "property.name.empty");
        }

        if (property.getName().length() < 3 || property.getName().length() > 60) {
            errors.rejectValue("name", "property.name.notproperlength");
        }

        if (property.getPrice() < 1) {
            errors.rejectValue("price", "property.price.notpositive");
        }

        if (property.getNumberOfRooms() < 1 || property.getNumberOfRooms() > 12) {
            errors.rejectValue("numberOfRooms", "property.rooms.notpositive");
        }

        if (property.getDescription() == null || property.getDescription().length() < 10) {
            errors.rejectValue("description", "property.description.empty");
        }


    }
}
