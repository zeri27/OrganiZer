package app.zeri.organizer.exceptions;

public class CompanyDoesNotExistException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public CompanyDoesNotExistException() {
        super("Company with company name does not exist");
    }
}
