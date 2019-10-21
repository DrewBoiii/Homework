package example.drew.homework.web.controller;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.web.dto.CarDto;
import example.drew.homework.web.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class MainController {

    private CarService carService;

    @Autowired
    public MainController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping({"/", "/home"})
    public String getIndexPage(){
        return "index";
    }

    @GetMapping("/cars")
    public String getCarsPage(Model model){
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
    public String getEditorPage(Model model){
        model.addAttribute("car", new CarDto());

        return "editor";
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping("/registration")
    public String getRegistrationForm(){
        return "registration";
    }

}
