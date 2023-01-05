package app.zeri.organizer.exceptions;

public class ProjectAlreadyAssignedToCompany extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public ProjectAlreadyAssignedToCompany() {
        super("Project already assigned to the requested company");
    }
}
