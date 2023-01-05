package app.zeri.organizer.exceptions;

public class UserAlreadyAssignedToCompany extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserAlreadyAssignedToCompany() {
        super("User already assigned to the requested company");
    }
}
