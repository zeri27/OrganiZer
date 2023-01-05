package app.zeri.organizer.exceptions;

public class ProjectAlreadyExistsException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public ProjectAlreadyExistsException() {
        super("Project already exists in the company");
    }
}
