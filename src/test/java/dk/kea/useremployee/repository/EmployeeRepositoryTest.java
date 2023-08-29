package dk.kea.useremployee.repository;

import dk.kea.useremployee.model.Employee;
import org.hibernate.JDBCException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void testAtLeastOneAnders(){
        List<Employee> employeeList = employeeRepository.findEmployeeByName("Anders");
        assertTrue(employeeList.size()>0);
    }

    @Test
    void testDeleteEmployee(){
        List<Employee> employeeList = employeeRepository.findEmployeeByName("Anders");
        Employee employee1 = employeeList.get(0);
        assertEquals("Anders", employee1.getName());
        //userRepository.delete(employee1.getUser());
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> userRepository.delete(employee1.getUser()));
    }

}