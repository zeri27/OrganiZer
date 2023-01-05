package app.zeri.organizer.exceptions;

public class CompanyAlreadyExistsException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public CompanyAlreadyExistsException() {
        super("Company with company name already exists");
    }
}
