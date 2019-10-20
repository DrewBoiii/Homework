package example.drew.homework.service;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.web.dto.CarDto;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getCars();

    Optional<Car> getCarById(Long id) throws CarNotFoundException;
    void submitCar(CarDto car);
    void deleteCarById(Long id);
    void updateCar(CarDto car);

    List<Car> getCarsByBrand(String brand);

}
