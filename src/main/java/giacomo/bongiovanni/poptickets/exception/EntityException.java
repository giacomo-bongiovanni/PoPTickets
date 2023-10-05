package giacomo.bongiovanni.poptickets.exception;

import lombok.Data;

@Data

public class EntityException extends RuntimeException{
    private String key;
    private String value;

    public EntityException(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
