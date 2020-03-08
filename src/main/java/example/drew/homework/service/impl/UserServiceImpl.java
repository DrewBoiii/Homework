package example.drew.homework.service.impl;

import example.drew.homework.exception.RoleNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.dao.RoleRepository;
import example.drew.homework.persistence.dao.UserRepository;
import example.drew.homework.persistence.model.Role;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.UserService;
import example.drew.homework.other.RoleConstants;
import example.drew.homework.web.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> save(UserDto dto) throws RoleNotFoundException {
        Optional<User> user = Optional.of(getInitializedUser(dto));

        Optional<Role> role = roleRepository.findByName(RoleConstants.ROLE_USER);

        Set<Role> roleSet = new HashSet<>();

        roleSet.add(role.orElseThrow(RoleNotFoundException::new));

        user.get().setRoles(roleSet);

        userRepository.save(user.get());

        log.info("Saved user is " + user.get().toString());

        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userRepository.findUserByUsername(username).orElse(null);
        log.info("Extracted user " + user);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        log.info("Extracted user " + user);
        return user;
    }

    private User getInitializedUser(UserDto dto){
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));

        Role role = new Role();
        role.setName(RoleConstants.ROLE_USER);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        user.setRoles(roleSet);

        return user;
    }
}
