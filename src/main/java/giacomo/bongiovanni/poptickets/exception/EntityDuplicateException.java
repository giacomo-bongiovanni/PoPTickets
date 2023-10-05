package giacomo.bongiovanni.poptickets.exception;



public class EntityDuplicateException extends EntityException {

    public EntityDuplicateException(String email) {
        super("email","email \""+email+"\" already present into the db,if your email is different,the problem is fiscalcode");
    }
}
