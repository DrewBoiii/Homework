package example.drew.homework.service;

import example.drew.homework.exception.RoleNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.model.User;
import example.drew.homework.web.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> save(UserDto userDto) throws RoleNotFoundException;

    User getUserByUsername(String username) throws UserNotFoundException;
    User getUserByEmail(String email) throws UserNotFoundException;

}
