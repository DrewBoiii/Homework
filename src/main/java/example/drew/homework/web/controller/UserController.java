package example.drew.homework.web.controller;

import example.drew.homework.persistence.model.User;
import example.drew.homework.service.dao.UserService;
import example.drew.homework.util.AjaxResponse;
import example.drew.homework.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> saveUser(@RequestBody UserRegistrationDto registrationDto){
        User user = userService.save(registrationDto);

        AjaxResponse<User> response = new AjaxResponse<>("success", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
