package app.zeri.organizer.exceptions;

public class UserDoesNotExistException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserDoesNotExistException() {
        super("User does not exist in the system");
    }
}
