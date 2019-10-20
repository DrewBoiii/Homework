package example.drew.homework.exception;

public class CarNotFoundException extends Exception {

    public CarNotFoundException() {
        this("Car was not found");
    }

    public CarNotFoundException(String message) {
        super(message);
    }
}
