package example.drew.homework.web.controller;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.util.CarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExampleAjaxController {

    private CarService carService;

    @Autowired
    public ExampleAjaxController(CarService carService) {
        this.carService = carService;
    }

//    @PostMapping("/saveCar")
//    public ResponseEntity<Object> saveCar(@RequestBody Car car){
//        carService.submitCar(car);
//        CarResponse<Car> response = new CarResponse<>("success", car);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @GetMapping("/getCars")
//    public ResponseEntity<Object> getCars(){
//        CarResponse<List<Car>> response = new CarResponse<>("success", carService.getCars());
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

}
