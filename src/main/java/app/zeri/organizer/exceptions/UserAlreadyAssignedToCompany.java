package app.zeri.organizer.exceptions;

public class UserAlreadyAssignedToProject extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserAlreadyAssignedToProject() {
        super("User already assigned to the requested project");
    }
}
