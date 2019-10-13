package example.drew.homework.service.dao;

import example.drew.homework.persistence.model.User;
import example.drew.homework.web.dto.UserRegistrationDto;

public interface UserService {

    User save(UserRegistrationDto userRegistrationDto);

}
