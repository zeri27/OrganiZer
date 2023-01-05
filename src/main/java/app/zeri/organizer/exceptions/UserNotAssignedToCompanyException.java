package app.zeri.organizer.exceptions;

public class UserNotAssignedToCompanyException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserNotAssignedToCompanyException() {
        super("User is not assigned to the requested company");
    }
}
