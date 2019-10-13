package example.drew.homework.service.model;

import example.drew.homework.persistence.dao.UserRepository;
import example.drew.homework.persistence.model.Role;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.dao.UserService;
import example.drew.homework.util.RoleConstants;
import example.drew.homework.web.dto.UserRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(UserRegistrationDto dto) {
        User user = getInitializedUser(dto);

        userRepository.save(user);

        log.info("Saved user is " + user.toString());

        return user;
    }

    private User getInitializedUser(UserRegistrationDto dto){
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));

        Role role = new Role();
        role.setName(RoleConstants.ROLE_USER);

        user.setRoles(Collections.singletonList(role));

        return user;
    }
}
