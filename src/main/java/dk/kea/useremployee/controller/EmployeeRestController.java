package dk.kea.useremployee.controller;

import dk.kea.useremployee.dto.EmployeeConverter;
import dk.kea.useremployee.dto.EmployeeDTO;
import dk.kea.useremployee.dto.UserConverter;
import dk.kea.useremployee.dto.UserDTO;
import dk.kea.useremployee.model.Employee;
import dk.kea.useremployee.model.User;
import dk.kea.useremployee.repository.EmployeeRepository;
import dk.kea.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRestController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    EmployeeConverter employeeConverter;


    @GetMapping("/employees")
    public List<EmployeeDTO> getEmployees(){
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        employees.forEach(e -> employeeDTOS.add(employeeConverter.toDTO(e)));
        return employeeDTOS;
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDTO postEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeConverter.toEntity(employeeDTO);
        employee.setId(0);
        employeeRepository.save(employee);
        System.out.println(employee);
        return employeeConverter.toDTO(employee);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    @GetMapping("/users")
    public List<UserDTO> getUsers(){
        List<User> users= userRepository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();

        users.forEach(u -> userDTOS.add(userConverter.toDTO(u)));
        return userDTOS;
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<EmployeeDTO> putEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO employeeDTO){
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()){
            Employee employee = employeeConverter.toEntity(employeeDTO);
            employee.setId(id);
            employeeRepository.save(employee);
            //return new ResponseEntity<>(student, HttpStatus.OK);
            return ResponseEntity.ok(employeeConverter.toDTO(employee));
        } else {
            //return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("employee/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()){
            userRepository.deleteById(optEmployee.get().getUser().getUserID());
            employeeRepository.deleteById(optEmployee.get().getId());
            //return new ResponseEntity<>(student, HttpStatus.OK);
            return ResponseEntity.ok("Student deleted👽");
        } else {
            //return new ResponseEntity<>(new Student(), HttpStatus.NOT_FOUND);
            return ResponseEntity.notFound().build();
        }
    }


}
