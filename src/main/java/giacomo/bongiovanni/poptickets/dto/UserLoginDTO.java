package giacomo.bongiovanni.poptickets.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginDTO {

    @Email
    @NotBlank
    @Size(min = 1, max = 50)
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
