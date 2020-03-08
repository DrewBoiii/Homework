package example.drew.homework.web.controller;

import example.drew.homework.exception.CarNotFoundException;
import example.drew.homework.exception.RoleNotFoundException;
import example.drew.homework.exception.UserNotFoundException;
import example.drew.homework.persistence.model.Car;
import example.drew.homework.persistence.model.Favorites;
import example.drew.homework.service.CarService;
import example.drew.homework.service.FavoritesService;
import example.drew.homework.service.UserService;
import example.drew.homework.web.dto.CarDto;
import example.drew.homework.web.dto.UserDto;
import example.drew.homework.web.filter.criteria.CarCriteria;
import example.drew.homework.web.filter.spec.CarSpecification;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class MainController {

    private static final int MAX_ELEMENTS_PER_PAGE = 6;
    private static final int MAX_INDEXES_RANGE_PER_PAGE = 5;

    private CarService carService;
    private UserService userService;
    private MessageSource messageSource;
    private FavoritesService favoritesService;

    public MainController(CarService carService, UserService userService, MessageSource messageSource, FavoritesService favoritesService) {
        this.carService = carService;
        this.userService = userService;
        this.messageSource = messageSource;
        this.favoritesService = favoritesService;
    }

    @GetMapping("/")
    public String getPage() {
        return "redirect:/home";
    }

    @ModelAttribute("car_criteria")
    public CarCriteria carCriteria() {
        return new CarCriteria();
    }

    @GetMapping("/home")
    public String getIndexPage(Model model, @ModelAttribute("car_criteria") CarCriteria carCriteria, @RequestParam(required = false) Optional<Integer> page) {
        Specification<Car> carSpecification = new CarSpecification(carCriteria);
        Sort sort = Sort.by(carCriteria.getSort()).descending();
        Page<Car> carPages =  carService.getCarsBySpecification(carSpecification, PageRequest.of(page.orElse(0), MAX_ELEMENTS_PER_PAGE, sort));
        int totalPages = carPages.getTotalPages();
        List<Integer> pageIndexes = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
        model.addAttribute("page_indexes", pageIndexes);
        model.addAttribute("active_page", page.orElse(0));
        model.addAttribute("view_range", MAX_INDEXES_RANGE_PER_PAGE);
        model.addAttribute("cars", carPages);
        return "index";
    }

    @GetMapping("/cars")
    public String getCarsPage(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("cars", carService.getCarsByUsername(user.getUsername()));
        return "cars";
    }

    @GetMapping("/cars/{car_id}/details")
    public String getCarDetails(@PathVariable("car_id") Long id, Model model, @AuthenticationPrincipal User authenticatedUser) throws CarNotFoundException, UserNotFoundException {
        Optional<example.drew.homework.persistence.model.User> person = Optional.of(userService.getUserByUsername(authenticatedUser.getUsername()));
        Optional<Car> car = carService.getCarById(id);
        car.ifPresent(car1 -> model.addAttribute("car", car1));
        boolean isAdded = favoritesService.findByPersonAndCar(person.orElse(new example.drew.homework.persistence.model.User()), car.orElse(new Car())).isPresent();
        model.addAttribute("isAdded", isAdded);
        return "details";
    }

    @PostMapping("/cars/{car_id}/details/add")
    public String addCarToFavorites(@PathVariable("car_id") Long id, Model model, @AuthenticationPrincipal User authenticatedUser) throws UserNotFoundException, CarNotFoundException {
        Optional<example.drew.homework.persistence.model.User> person = Optional.of(userService.getUserByUsername(authenticatedUser.getUsername()));
        Optional<Car> car = carService.getCarById(id);
        Favorites favorites = new Favorites();
        person.ifPresent(favorites::setPerson);
        car.ifPresent(favorites::setCar);
        favoritesService.save(favorites);
        return "redirect:/cars/{car_id}/details";
    }

    @PostMapping("/cars/{car_id}/details/remove")
    public String removeCarFromFavorites(@PathVariable("car_id") Long id, Model model, @AuthenticationPrincipal User authenticatedUser) throws UserNotFoundException, CarNotFoundException {
        Optional<example.drew.homework.persistence.model.User> person = Optional.of(userService.getUserByUsername(authenticatedUser.getUsername()));
        Optional<Car> car = carService.getCarById(id);
        favoritesService.deleteByPerson_IdAndCar_Id(person.get().getId(), car.get().getId());
        return "redirect:/cars/{car_id}/details";
    }

    @GetMapping("/cars/{car_id}/editor")
    public String getCarEditor(@PathVariable("car_id") Long id, Model model) throws CarNotFoundException {
        Optional<Car> car = carService.getCarById(id);

        car.ifPresent(car1 -> model.addAttribute("car", car1));

        return "editor";
    }

    @GetMapping("/editor")
    public String getEditorPage(Model model) {
        model.addAttribute("car", new CarDto());

        return "editor";
    }

    @ModelAttribute("user")
    public UserDto userRegistrationDto() {
        return new UserDto();
    }

    @GetMapping("/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, Errors errors) throws RoleNotFoundException, UserNotFoundException {
        example.drew.homework.persistence.model.User userByEmail = userService.getUserByEmail(userDto.getEmail());
        if (userByEmail != null) {
            errors.rejectValue(
                    "email",
                    HttpStatus.BAD_REQUEST.toString(),
                    "There is already an account registered with that email"
            );
        }
        example.drew.homework.persistence.model.User userByUsername = userService.getUserByUsername(userDto.getUsername());
        if (userByUsername != null) {
            errors.rejectValue(
                    "username",
                    HttpStatus.BAD_REQUEST.toString(),
                    "There is already an account registered with that username"
            );
        }
        if (errors.hasErrors()) {
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
