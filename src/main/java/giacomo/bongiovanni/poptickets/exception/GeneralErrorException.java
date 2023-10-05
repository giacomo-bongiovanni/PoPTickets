package giacomo.bongiovanni.poptickets.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GeneralErrorException extends RuntimeException{
    private long id;
    public GeneralErrorException(long id) {
        this.id = id;
    }
}
