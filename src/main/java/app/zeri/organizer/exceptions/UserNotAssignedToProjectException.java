package app.zeri.organizer.exceptions;

public class UserNotAssignedToProjectException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserNotAssignedToProjectException() {
        super("User is not assigned to the requested project");
    }
}
