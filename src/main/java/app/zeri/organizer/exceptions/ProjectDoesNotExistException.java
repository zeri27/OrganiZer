package app.zeri.organizer.exceptions;

public class ProjectDoesNotExistException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public ProjectDoesNotExistException() {
        super("Project does not exist in the company");
    }
}
