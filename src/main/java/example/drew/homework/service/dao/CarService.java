package example.drew.homework.service.dao;

import example.drew.homework.persistence.model.Car;
import example.drew.homework.web.dto.CarDto;

import java.util.List;

public interface CarService {

    List<Car> getCars();

    Car getCarById(Long id);
    void submitCar(CarDto car);
    void deleteCarById(Long id);
    void updateCar(CarDto car);

    List<Car> getCarsByBrand(String brand);

}
