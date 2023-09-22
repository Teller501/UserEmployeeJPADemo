package dk.kea.useremployee.dto;

import dk.kea.useremployee.model.Gender;
import dk.kea.useremployee.model.User;

import java.time.LocalDateTime;

public record EmployeeDTO(int id, String name, UserDTO user, Gender gender) {
}
