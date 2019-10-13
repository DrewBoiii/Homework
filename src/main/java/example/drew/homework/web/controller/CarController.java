package example.drew.homework.web.controller;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.dao.CarService;
import example.drew.homework.util.AjaxResponse;
import example.drew.homework.web.dto.CarDto;
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
    public ResponseEntity<Object> saveCar(@RequestBody CarDto carDto){
        carService.submitCar(carDto);

        AjaxResponse<CarDto> response = new AjaxResponse<>("success", carDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cars/all")
    public ResponseEntity<Object> getCars(){
        AjaxResponse<List<Car>> response = new AjaxResponse<>("success", carService.getCars());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/cars/{car_id}/delete")
    public ResponseEntity<Object> deleteCar(@PathVariable("car_id") Long id){
        Car removedCar = carService.getCarById(id);
        carService.deleteCarById(id);

        AjaxResponse<Car> response = new AjaxResponse<>("success", removedCar);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/cars/{car_id}/update")
    public ResponseEntity<Object> updateCar(@PathVariable("car_id") Long id, @RequestBody CarDto carDto){
        carService.updateCar(carDto);
        System.out.println(id);
        AjaxResponse<CarDto> response = new AjaxResponse<>("success", carDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
