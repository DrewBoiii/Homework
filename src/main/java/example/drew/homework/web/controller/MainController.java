package example.drew.homework.web.controller;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.service.UserService;
import example.drew.homework.web.dto.CarDto;
import example.drew.homework.web.dto.UserDto;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Locale;
import java.util.Optional;

@Controller
public class MainController {

    private CarService carService;
    private UserService userService;
    private MessageSource messageSource;

    public MainController(CarService carService, UserService userService, MessageSource messageSource) {
        this.carService = carService;
        this.userService = userService;
        this.messageSource = messageSource;
    }

    @GetMapping({"/", "/home"})
    public String getIndexPage(Model model, @AuthenticationPrincipal User user) throws UserNotFoundException {
        Optional<example.drew.homework.persistence.model.User> person = Optional.empty();

        if(user != null) {
            person = userService.findUserByUsername(user.getUsername());
        }

        String name = "Anonymous";

        if(person.isPresent()) {
            name = person.get().getUsername();
        }

        model.addAttribute("name", messageSource.getMessage("greeting.user", new String[]{name}, Locale.getDefault()));

        return "index";
    }

    @GetMapping("/cars")
    public String getCarsPage(Model model) {
        model.addAttribute("cars", carService.getCars());
        return "cars";
    }

    @GetMapping("/cars/{car_id}/details")
    public String getCarDetails(@PathVariable("car_id") Long id, Model model) throws CarNotFoundException {
        Optional<Car> car = carService.getCarById(id);

        car.ifPresent(car1 -> model.addAttribute("car", car1));

        return "details";
    }

    @GetMapping("/editor")
    public String getEditorPage(Model model) {
        model.addAttribute("car", new CarDto());

        return "editor";
    }

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
