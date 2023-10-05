package giacomo.bongiovanni.poptickets.exception;



public class UserDuplicateException extends UserException {

    public UserDuplicateException(String email) {
        super("email","email \""+email+"\" already present into the db,if your email is different,the problem is fiscalcode");
    }
}
