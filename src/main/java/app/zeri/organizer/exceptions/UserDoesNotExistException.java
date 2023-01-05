package app.zeri.organizer.exceptions;

public class UserAlreadyExistsException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserAlreadyExistsException() {
        super("User with email already exists");
    }
}
