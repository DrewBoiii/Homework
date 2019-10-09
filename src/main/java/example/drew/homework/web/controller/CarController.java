package example.drew.homework.web.controller;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.util.CarResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/submit")
    public ResponseEntity<Object> saveCar(@RequestBody Car car){
        carService.submitCar(car);

        CarResponse<Car> response = new CarResponse<>("success", car);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cars/all")
    public ResponseEntity<Object> getCars(){
        CarResponse<List<Car>> response = new CarResponse<>("success", carService.getCars());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{car_id}/delete")
    public ResponseEntity<Object> deleteCar(@PathVariable("car_id") Long id){
        Car removedCar = carService.getCarById(id);
        carService.deleteCarById(id);

        CarResponse<Car> response = new CarResponse<>("success", removedCar);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/cars/{car_id}/update")
    public ResponseEntity<Object> updateCar(@PathVariable("car_id") Long id, @RequestBody Car car){
        carService.updateCar(car);
        System.out.println(id);
        CarResponse<Car> response = new CarResponse<>("success", car);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
