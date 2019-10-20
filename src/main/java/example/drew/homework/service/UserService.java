package example.drew.homework.service;

import example.drew.homework.persistence.model.User;
import example.drew.homework.web.dto.UserRegistrationDto;

import java.util.Optional;

public interface UserService {

    Optional<User> save(UserRegistrationDto userRegistrationDto);

}
