package app.zeri.organizer.exceptions;

public class UserAuthenticationFailedException extends Exception {

    static final long serialVersionUID = -3387536993156229948L;

    public UserAuthenticationFailedException() {
        super("Email or password incorrect");
    }
}
