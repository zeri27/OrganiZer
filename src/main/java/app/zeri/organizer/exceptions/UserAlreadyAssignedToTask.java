package app.zeri.organizer.exceptions;

public class UserAlreadyAssignedToTask extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserAlreadyAssignedToTask() {
        super("User already assigned to the requested task");
    }
}
