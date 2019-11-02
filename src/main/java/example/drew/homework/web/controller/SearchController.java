package example.drew.homework.web.controller;

import example.drew.homework.util.SearchCriteria;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class SearchController {

    private CarService carService;

    @Autowired
    public SearchController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/api/search")
    public ResponseEntity<?> getSearchResultViaAjax(@Valid @RequestBody SearchCriteria search) {

//        AjaxResponseDto response = new AjaxResponseDto();

        //If error, just return a 400 bad request, along with the error message
//        if (errors.hasErrors()) {
//
//            result.setMessage(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
//            return ResponseEntity.badRequest().body(result);
//
//        }
        

        List<Car> cars = carService.getCarsByBrand(search.getBrand());

//        if (cars.isEmpty()) {
//            response.setMessage("no car was found!");
//        } else {
//            response.setMessage("success");
//        }
//
//        response.setResult(cars);
//
//        return ResponseEntity.ok(response);
        return null;

    }

}
