package giacomo.bongiovanni.poptickets.dto;

import giacomo.bongiovanni.poptickets.model.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private long id;
    @NotBlank(message = "name cannot be blank")
    @Size(max = 30, message = "name cannot be longer than 30 character")
    private String name;

    @Size(max = 30, message = "surname cannot be longer than 30 character")
    private String surname;

    @Email(message = "email is not a well-formed email address")
    @NotBlank(message = "email cannot be blank")
    @Size(max = 50, message = "email cannot be longer than 50 character")
    private String email;

    @NotBlank(message = "password cannot be blank")
    @Size(max = 50, message = "password cannot be longer than 50 character")
    private String password;

    @Past(message = "birth date cannot be a future date")
    @NotNull
    private LocalDate birthDate;

    @Size(max = 20, message = "fiscalCode cannot be longer than 20 character")
    @NotNull
    private String fiscalCode;

    private String role;

    private boolean blocked;

    private LocalDateTime createdAt = LocalDateTime.now();

    private LocalDateTime deletedAt;
}
