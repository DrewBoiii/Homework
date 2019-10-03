package example.drew.homework.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    @GetMapping("/")
    public String getCarsPage(){
        return "cars";
    }

    @GetMapping("/editor")
    public String getEditorPage(){
        return "editor";
    }

    @GetMapping("/details/{car_id}")
    public String getCarById(@PathVariable("car_id") Long id){
        // TODO: 03.10.2019 get car by id
        return "details";
    }

    @PostMapping("/editor/submit")
    public String submitCar(){
        // TODO: 03.10.2019 save to database
        return "redirect:../";
    }

    @DeleteMapping("/editor/delete/{car_id}")
    public String deleteCarById(@PathVariable("car_id") Long id){
        // TODO: 03.10.2019 remove from database
        return "redirect:../";
    }

    @PutMapping("/editor/update/{car_id}")
    public String updateCarById(@PathVariable("car_id") Long id){
        // TODO: 03.10.2019 update database
        return "redirect:../";
    }

}
