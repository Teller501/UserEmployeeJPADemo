package dk.kea.useremployee.dto;

import dk.kea.useremployee.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {

    @Autowired
    UserConverter userConverter;

    public Employee toEntity(EmployeeDTO employeeDTO){
        return new Employee(
                employeeDTO.id(),
                employeeDTO.name(),
                employeeDTO.gender(),
                userConverter.toEntity(employeeDTO.user())
        );
    }

    public EmployeeDTO toDTO(Employee employee){
        return new EmployeeDTO(
                employee.getId(),
                employee.getName(),
                userConverter.toDTO(employee.getUser()),
                employee.getGender()
        );
    }
}
