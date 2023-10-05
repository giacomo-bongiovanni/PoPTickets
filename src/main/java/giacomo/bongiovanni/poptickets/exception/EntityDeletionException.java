package giacomo.bongiovanni.poptickets.exception;

public class EntityDeletionException extends EntityException {

    public EntityDeletionException(long id) {
        super("id","can't delete user with this id = "+id);
    }
}
