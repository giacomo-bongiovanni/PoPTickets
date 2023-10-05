package giacomo.bongiovanni.poptickets.exception;


public class UserCreationExeption extends UserException{
    public UserCreationExeption(long id) {
        super("id","there was an error with user with this id = "+id);
    }
}
