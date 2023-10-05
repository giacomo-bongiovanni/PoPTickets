package giacomo.bongiovanni.poptickets.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

public class UserDeletionException extends UserException{

    public UserDeletionException(long id) {
        super("id","can't delete user with this id = "+id);
    }
}
