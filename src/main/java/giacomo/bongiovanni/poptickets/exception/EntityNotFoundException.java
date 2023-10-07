package giacomo.bongiovanni.poptickets.exception;

public class EntityNotFoundException extends EntityException {

    public EntityNotFoundException(long id) {
        super("id","can't found entity with id = "+ id);
    }
    public EntityNotFoundException(String email_name) {
        super("email","can't find entity with email/name = "+ email_name);
    }

}
