package example.drew.homework.service.impl;

import example.drew.homework.persistence.dao.UserRepository;
import example.drew.homework.persistence.model.Role;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.UserService;
import example.drew.homework.util.RoleConstants;
import example.drew.homework.web.dto.UserRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> save(UserRegistrationDto dto) {
        User user = getInitializedUser(dto);

        userRepository.save(user);

        log.info("Saved user is " + user.toString());

        return new Optional<>(user);
    }

    private User getInitializedUser(UserRegistrationDto dto){
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role role = new Role();
        role.setName(RoleConstants.ROLE_USER);

        user.setRoles(Collections.singletonList(role));

        return user;
    }
}
