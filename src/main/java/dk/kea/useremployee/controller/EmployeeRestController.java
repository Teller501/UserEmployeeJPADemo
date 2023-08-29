package dk.kea.useremployee.controller;

import dk.kea.useremployee.model.Employee;
import dk.kea.useremployee.model.User;
import dk.kea.useremployee.repository.EmployeeRepository;
import dk.kea.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeRestController {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/employees")
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping("/employee")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee postEmployee(@RequestBody Employee employee){
        System.out.println(employee);
        return employeeRepository.save(employee);
    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    @PutMapping("employee/{id}")
    public ResponseEntity<Employee> putEmployee(@PathVariable("id") int id, @RequestBody Employee employee){
        Optional<Employee> optEmployee = employeeRepository.findById(id);
        if (optEmployee.isPresent()){
            employee.setId(id);
            employeeRepository.save(employee);
            //return new ResponseEntity<>(student, HttpStatus.OK);
            return ResponseEntity.ok(employee);
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
