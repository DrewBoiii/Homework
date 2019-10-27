package example.drew.homework.exception;

public class RoleNotFoundException extends Exception {

    public RoleNotFoundException() {
        this("Role was not found");
    }

    public RoleNotFoundException(String message) {
        super(message);
    }
}
