package giacomo.bongiovanni.poptickets.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDTO {
    private LocalDateTime date;
    private Map<String,String> messages;
    private String path;
}
