package example.drew.homework.service;

import example.drew.homework.persistence.dao.CarRepository;
import example.drew.homework.persistence.model.Car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
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

    @Override
    public List<Car> getCars() {
        return StreamSupport
                .stream(Spliterators.spliteratorUnknownSize(carRepository.findAll().iterator(), Spliterator.NONNULL), false)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public Car getCarById(Long id) {
        Car car;
        if(carRepository.existsById(id) && carRepository.findById(id).isPresent()){
            car = carRepository.findById(id).get();

            log.info("Extracted car is " + car.toString());

            return car;
        }

        log.info("Extracted car is null");

        return null;
    }

    @Override
    public void submitCar(Car car) {
        Car savedCar = carRepository.save(car);

        log.info("Saved car is " + savedCar.toString());
    }

    @Override
    public void deleteCarById(Long id) {
        Car removedCar;
        if(carRepository.existsById(id) && carRepository.findById(id).isPresent()){
            removedCar = carRepository.findById(id).get();

            log.info("Removed car is " + removedCar.toString());

            carRepository.delete(removedCar);
        }
    }

    @Override
    public void updateCar(Car car) {
        Car oldCar;
        if(carRepository.existsById(car.getId()) && carRepository.findById(car.getId()).isPresent()){
            oldCar = carRepository.findById(car.getId()).get();

            oldCar.setBrand(car.getBrand());
            oldCar.setModel(car.getModel());
            oldCar.setBuild(car.getBuild());
            oldCar.setKilometers(car.getKilometers());

            carRepository.save(oldCar);

            log.info("Updated car is " + oldCar.toString());
        }
    }

    @Override
    public List<Car> getCarsByBrand(String brand) {
        return carRepository.findCarsByBrand(brand);
    }
}
