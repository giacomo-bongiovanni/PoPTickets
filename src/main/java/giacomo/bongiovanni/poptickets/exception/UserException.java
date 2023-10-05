package giacomo.bongiovanni.poptickets.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data

public class UserException extends RuntimeException{
    private String key;
    private String value;

    public UserException(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
