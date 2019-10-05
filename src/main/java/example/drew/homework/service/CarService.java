package example.drew.homework.service;

import example.drew.homework.persistence.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getCars();

    Car getCarById(Long id);
    void submitCar(Car car);
    void deleteCarById(Long id);
    void updateCarById(Long id);

    List<Car> getCarsByBrand(String brand);

}