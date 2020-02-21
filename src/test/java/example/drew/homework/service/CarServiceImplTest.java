package example.drew.homework.service;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.persistence.dao.CarRepository;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.persistence.model.User;
import example.drew.homework.service.impl.CarServiceImpl;
import example.drew.homework.web.dto.CarDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService = new CarServiceImpl(carRepository, null);

    private Optional<Car> expectedCar;

    private CarDto carDto;

    @Before
    public void init() {
        this.carDto = new CarDto(1L, "brand", "model", 123L, LocalDate.now(), "description", new User());

        Car car = getInitCar();

        this.expectedCar = Optional.of(car);

        Mockito.when(carRepository.findCarsOrderByCreatedAt()).thenReturn(Collections.emptyList());
        Mockito.when(carRepository.findCarsBySearchCriteria("brand")).thenReturn(Collections.emptyList());
        Mockito.when(carRepository.findById(1L)).thenReturn(this.expectedCar);
//        Mockito.when(carRepository.findById(111L)).thenThrow(new CarNotFoundException());
    }

    private Car getInitCar(){
        Car car = new Car();

        car.setId(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setKilometers(carDto.getKilometers());
        car.setBuild(carDto.getBuild());
        car.setPerson(carDto.getPerson());

        return car;
    }

    @After
    public void destroy() {
        this.carDto = null;
        this.expectedCar = Optional.empty();
    }

    @Test
    public void getCarsOrderByCreatedAtField_whenNoArgs_returnNotNullList() {
        List<Car> cars = carService.getCars();

        Assert.assertNotNull(cars);
    }

    @Test
    public void getCarById_whenPassingArgId_returnNotEmptyOptional() throws CarNotFoundException {
        Optional<Car> actualCar = carService.getCarById(1L);

        Assert.assertNotNull(actualCar);

        Assert.assertEquals(expectedCar, actualCar);
    }

    @Test
    public void getCarsByBrand_whenPassingArgBrand_returnNotNullList() {
        List<Car> cars = carService.getCarsBySearchCriteria("brand");

        Assert.assertNotNull(cars);
    }

    @Test
    public void getNumberOfInvocation_whenCallGetCarByBrandMethod_returnOneTime(){
        carService.getCarsBySearchCriteria(Mockito.anyString());

        Mockito.verify(carRepository, new Times(1)).findCarsBySearchCriteria(Mockito.anyString());
    }

}
