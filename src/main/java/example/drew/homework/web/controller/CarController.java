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

    /*
    @GetMapping
    public String getCarsPage(Model model){
        model.addAttribute("cars", carService.getCars());

        return "cars";
    }

    @GetMapping("/editor")
    public String getEditorPage(Model model){
        model.addAttribute("car", new Car());

        return "editor";
    }

    @GetMapping("/{car_id}/details")
    public String getCarById(@PathVariable("car_id") Long id, Model model){
        model.addAttribute("car", carService.getCarById(id));

        return "details";
    }

    @PostMapping("/editor/submit")
    public String submitCar(@ModelAttribute Car car, BindingResult result){
        carService.submitCar(car);

        return "redirect:../";
    }

//    @DeleteMapping("/editor/delete/{car_id}")
    @RequestMapping("/editor/delete/{car_id}")
    public String deleteCarById(@PathVariable("car_id") Long id){
        carService.deleteCarById(id);

        return "redirect:/cars";
    }

    @PutMapping("/{car_id}/update")
    public String updateCarById(@PathVariable("car_id") Long id){
        // TODO: 03.10.2019 update database
        return "redirect:/cars/{car_id}/details";
    }
    */

}
