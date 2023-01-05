package app.zeri.organizer.exceptions;

public class TaskDoesNotExistException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public TaskDoesNotExistException() {
        super("Task does not exist in the project");
    }
}
