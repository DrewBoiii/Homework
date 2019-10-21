package example.drew.homework.web.controller;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.service.UserService;
import example.drew.homework.web.dto.AjaxResponseDto;
import example.drew.homework.web.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private CarService carService;
    private UserService userService;

    @Autowired
    public CarController(CarService carService, UserService userService) {
        this.carService = carService;
        this.userService = userService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> saveCar(@RequestBody CarDto carDto, @AuthenticationPrincipal User user) throws UserNotFoundException {
        Optional<example.drew.homework.persistence.model.User> person = userService.findUserByUsername(user.getUsername());

        if(person.isPresent()) {
            carDto.setPerson(person.get());

            carService.saveCar(carDto);

            AjaxResponseDto<CarDto> response = new AjaxResponseDto<>("success", carDto);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        AjaxResponseDto<CarDto> response = new AjaxResponseDto<>("failed", null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cars/all")
    public ResponseEntity<Object> getCars() {
        AjaxResponseDto<List<Car>> response = new AjaxResponseDto<>("success", carService.getCars());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{car_id}/delete")
    public ResponseEntity<Object> deleteCar(@PathVariable("car_id") Long id) throws CarNotFoundException {
        Optional<Car> removedCar = carService.getCarById(id);

        if (removedCar.isPresent()) {
            carService.deleteCarById(id);

            AjaxResponseDto<Car> response = new AjaxResponseDto<>("success", removedCar.get());

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        AjaxResponseDto<Car> response = new AjaxResponseDto<>("failed", null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/cars/{car_id}/update")
    public ResponseEntity<Object> updateCar(@PathVariable("car_id") Long id, @RequestBody CarDto carDto) throws CarNotFoundException {
        Optional<Car> updatedCar = carService.getCarById(id);

        if (updatedCar.isPresent()) {
            carDto.setId(id);

            carService.updateCar(carDto);

            AjaxResponseDto<CarDto> response = new AjaxResponseDto<>("success", carDto);

            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        AjaxResponseDto<Car> response = new AjaxResponseDto<>("failed", null);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

}
