package example.drew.homework.service.impl;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.persistence.dao.CarRepository;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.web.dto.CarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // TODO: 20.10.2019 this using database through query
    @Override
    public List<Car> getCars() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(carRepository.findAll().iterator(), Spliterator.NONNULL), false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Car> getCarById(Long id) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(id);
        if(car.isPresent()){

            log.info("Extracted car is " + car.get().toString());

            return car;
        }

        log.info("Extracted car is null");

        throw  new CarNotFoundException();
    }

    @Override
    public void submitCar(CarDto carDto) {
        Car savedCar = getInitializedCar(carDto);

        carRepository.save(savedCar);

        log.info("Saved car is " + savedCar.toString());
    }

    @Override
    public void deleteCarById(Long id) {
        Optional<Car> removedCar = carRepository.findById(id);
        if(removedCar.isPresent()){

            log.info("Removed car is " + removedCar.get().toString());

            carRepository.delete(removedCar.get());
        }
    }

    @Override
    public void updateCar(CarDto carDto) {
        Car newCar = getInitializedCar(carDto);
        Car oldCar;

        if(carRepository.findById(newCar.getId()).isPresent()){
            oldCar = carRepository.findById(newCar.getId()).get();

            oldCar.setBrand(newCar.getBrand());
            oldCar.setModel(newCar.getModel());
            oldCar.setBuild(newCar.getBuild());
            oldCar.setKilometers(newCar.getKilometers());

            carRepository.save(oldCar);

            log.info("Updated car is " + oldCar.toString());
        }
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findCarsByBrand(brand);
    }

    private Car getInitializedCar(CarDto dto){
        Car car = new Car();

        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setBuild(dto.getBuild());
        car.setKilometers(dto.getKilometers());

        return car;
    }
}
