package ua.training.model.dao;

import ua.training.model.entity.Employee;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Employee.ROLE, String>{

    @Override
    public String convertToDatabaseColumn(Employee.ROLE attribute) {
        return attribute.name();
    }

    @Override
    public Employee.ROLE convertToEntityAttribute(String dbData) {
        return Employee.ROLE.valueOf(dbData);
    }
}
