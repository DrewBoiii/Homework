package example.drew.homework.web.controller;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

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
    public String submitCar(@ModelAttribute Car car){
        carService.submitCar(car);

        return "redirect:../";
    }

    @DeleteMapping("/editor/delete/{car_id}")
    public String deleteCarById(@PathVariable("car_id") Long id){
        carService.deleteCarById(id);

        return "redirect:/cars";
    }

    @PutMapping("/{car_id}/update")
    public String updateCarById(@PathVariable("car_id") Long id){
        // TODO: 03.10.2019 update database
        return "redirect:/cars/{car_id}/details";
    }

}
