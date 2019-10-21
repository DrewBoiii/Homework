package example.drew.homework.web.controller;

import example.drew.homework.persistence.model.User;
import example.drew.homework.service.UserService;
import example.drew.homework.web.dto.AjaxResponseDto;
import example.drew.homework.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> saveUser(@Valid @ModelAttribute("user") UserDto registrationDto, Errors userBlank){
        if(userBlank.hasErrors()){
            AjaxResponseDto<User> response = new AjaxResponseDto<>("failed", null);

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Optional<User> user = userService.save(registrationDto);

        AjaxResponseDto<User> response = null;

        if(user.isPresent()) {
            response = new AjaxResponseDto<>("success", user.get());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
