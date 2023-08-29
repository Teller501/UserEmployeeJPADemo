package dk.kea.useremployee.config;

import dk.kea.useremployee.model.Employee;
import dk.kea.useremployee.model.Gender;
import dk.kea.useremployee.model.User;
import dk.kea.useremployee.repository.EmployeeRepository;
import dk.kea.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InitData implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) throws Exception {
        User us1 = new User();
        us1.setEmail("test@gmail.com");
        us1.setPassword("test123");
        userRepository.save(us1);

        us1.setUserID(0);
        us1.setEmail("test2@gmail.com");
        us1.setPassword("test1234");
        userRepository.save(us1);

        us1.setUserID(0);
        us1.setEmail("test3@gmail.com");
        us1.setPassword("test12345");
        userRepository.save(us1);

        Employee employee = new Employee();
        employee.setBorn(LocalDateTime.of(1990,5,10,16,20,25));
        employee.setName("Anders");
        employee.setGender(Gender.MALE);
        employee.setVegetarian(false);
        employee.setUser(us1);
        employeeRepository.save(employee);
    }
}
