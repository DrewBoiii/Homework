package example.drew.homework.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        this("User was not found");
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
