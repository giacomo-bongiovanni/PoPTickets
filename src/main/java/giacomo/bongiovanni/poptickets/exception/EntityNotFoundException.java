package giacomo.bongiovanni.poptickets.exception;

public class EntityNotFoundException extends EntityException {

    public EntityNotFoundException(long id) {
        super("id","can't found user with id = "+ id);
    }
    public EntityNotFoundException(String email) {
        super("email","can't find user with email = "+ email);
    }

}
