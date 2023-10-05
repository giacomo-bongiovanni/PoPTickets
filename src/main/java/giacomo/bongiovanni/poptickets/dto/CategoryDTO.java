package giacomo.bongiovanni.poptickets.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {

    private long id;
    @NotBlank
    @Size(min = 1,max = 30)
    private String name;
}
