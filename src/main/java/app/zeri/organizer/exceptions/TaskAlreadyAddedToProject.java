package app.zeri.organizer.exceptions;

public class TaskAlreadyAddedToProject extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public TaskAlreadyAddedToProject() {
        super("Task already assigned to project");
    }
}
