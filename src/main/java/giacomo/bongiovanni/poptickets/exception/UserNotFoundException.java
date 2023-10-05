package giacomo.bongiovanni.poptickets.exception;

public class UserNotFoundException extends UserException{

    public UserNotFoundException(long id) {
        super("id","can't found user with id = "+ id);
    }
    public UserNotFoundException(String email) {
        super("email","can't find user with email = "+ email);
    }

}
