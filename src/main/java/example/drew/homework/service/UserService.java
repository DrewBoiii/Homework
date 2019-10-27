package example.drew.homework.service;

import example.drew.homework.exception.RoleNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.model.User;
import example.drew.homework.web.dto.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<User> save(UserDto userDto) throws RoleNotFoundException;

    Optional<User> findUserByUsername(String username) throws UserNotFoundException;

}
