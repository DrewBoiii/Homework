package example.drew.homework.service.impl;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.persistence.dao.CarRepository;
import example.drew.homework.persistence.dao.FavoritesRepository;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.service.CarService;
import example.drew.homework.web.dto.CarDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;
    private FavoritesRepository favoritesRepository;

    public CarServiceImpl(CarRepository carRepository, FavoritesRepository favoritesRepository) {
        this.carRepository = carRepository;
        this.favoritesRepository = favoritesRepository;
    }

    @Override
    public List<Car> getCars() {
        return carRepository.findCarsOrderByCreatedAt();
    }

    @Override
    public List<Car> getCarsByUsername(String username) {
        return carRepository.findCarsByPerson_Username(username);
    }

    @Override
    public Optional<Car> getCarById(Long id) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(id);

        car.ifPresent(car1 -> log.info("Extracted car is " + car1.toString()));

        return Optional.of(car.orElseThrow(CarNotFoundException::new));
    }

    @Override
    public void saveCar(CarDto carDto) {
        Optional<Car> savedCar = Optional.of(getInitializedCar(carDto));

        savedCar.get().setPerson(carDto.getPerson());

        carRepository.save(savedCar.get());

        log.info("Saved car is " + savedCar.get().toString());
    }

    @Override
    public void deleteCarById(Long id) {
        Car carToRemove = carRepository.findById(id).orElse(new Car());

        log.info("Removed car is " + carToRemove);

        carRepository.delete(carToRemove);
        favoritesRepository.deleteByPerson_IdAndCar_Id(carToRemove.getPerson().getId(), carToRemove.getId());
    }

    @Override
    public void updateCar(CarDto carDto) {
        Optional<Car> newCar = Optional.of(getInitializedCar(carDto));
        Optional<Car> oldCar = carRepository.findById(newCar.get().getId());

        if (oldCar.isPresent()) {

            oldCar.get().setBrand(newCar.get().getBrand());
            oldCar.get().setModel(newCar.get().getModel());
            oldCar.get().setBuild(newCar.get().getBuild());
            oldCar.get().setKilometers(newCar.get().getKilometers());
            oldCar.get().setDescription(newCar.get().getDescription());

            carRepository.save(oldCar.get());

            log.info("Updated car is " + oldCar.get().toString());
        }
    }

    @Override
    public List<Car> getCarsBySearchCriteria(String searchCriteria) {
        return carRepository.findCarsBySearchCriteria(searchCriteria);
    }

//    @Override
//    public List<Car> getCarsBySpecification(Specification<Car> specification) {
//        return carRepository.findAll(specification);
//    }

    @Override
    public Page<Car> getCarsBySpecification(Specification<Car> specification, Pageable pageable) {
        return carRepository.findAll(specification, pageable);
    }

    private Car getInitializedCar(CarDto dto) {
        Car car = new Car();

        car.setId(dto.getId());
        car.setBrand(dto.getBrand());
        car.setModel(dto.getModel());
        car.setBuild(dto.getBuild());
        car.setKilometers(dto.getKilometers());
        car.setDescription(dto.getDescription());

        return car;
    }
}
