package dk.kea.useremployee.dto;

import dk.kea.useremployee.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public User toEntity(UserDTO userDTO){
        return new User(
                userDTO.userID(),
                userDTO.email()
        );
    }

    public UserDTO toDTO(User user){
        return new UserDTO(
                user.getUserID(),
                user.getEmail()
        );
    }
}
