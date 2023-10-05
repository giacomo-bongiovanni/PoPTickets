package giacomo.bongiovanni.poptickets.exception;


public class EntityCreationExeption extends EntityException {
    public EntityCreationExeption(long id) {
        super("id","there was an error with user with this id = "+id);
    }
}
