package example.drew.homework.service;

import example.drew.homework.exception.RoleNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.dao.RoleRepository;
import example.drew.homework.persistence.dao.UserRepository;
import example.drew.homework.persistence.model.Role;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.impl.UserServiceImpl;
import example.drew.homework.util.RoleConstants;
import example.drew.homework.web.dto.UserDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder encoder;

    @InjectMocks
    private UserService userService = new UserServiceImpl(userRepository, roleRepository, encoder);

    private UserDto userDto;

    private Optional<User> expectedUser;

    @Before
    public void initUserDtoAndEncoder() {
        this.encoder = new BCryptPasswordEncoder();
        this.userDto = new UserDto("goyko","mitich@mail.ru", null, null);

        User user = getInitUser();

        this.expectedUser = Optional.of(user);

        Mockito.when(userRepository.findUserByUsername("goyko")).thenReturn(this.expectedUser);

        Mockito.when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(new Role(null, RoleConstants.ROLE_USER)));
    }

    private User getInitUser(){
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        Set<Role> roles = new HashSet<>(Collections.emptySet());
        roles.add(new Role(null, RoleConstants.ROLE_USER));
        user.setRoles(roles);

        return user;
    }

    @After
    public void destroyUserDtoAndEncoder() {
        this.userDto = null;
        this.encoder = null;
        this.expectedUser.ifPresent(user -> user.setRoles(null));
        this.expectedUser = Optional.empty();
    }

    @Test
    public void saveUser_whenCorrectUserDto_returnNotEmptyOptional() throws RoleNotFoundException {
        Optional<User> actualUser = userService.save(this.userDto);

        Assert.assertNotNull(actualUser);

        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void getOptionalUser_whenPassingArgUsername_returnNotEmptyOptional() throws UserNotFoundException {
        String username = "goyko";

        Optional<User> actualUser = userService.findUserByUsername(username);

        Assert.assertNotNull(actualUser);

        Assert.assertEquals(expectedUser, actualUser);
    }

}
