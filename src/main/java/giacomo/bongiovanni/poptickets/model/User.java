package giacomo.bongiovanni.poptickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User extends DefaultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 50)
    private String name;
    @Column(nullable = false,length = 50)
    private String surname;
    @Column(nullable = false,unique = true,length = 50)
    private String email;
    @Column(nullable = false,length = 30)
    private String password;
    @Column(nullable = false,length = 20)
    private LocalDate birthDate;
    @Column(nullable = false,unique = true,length = 30)
    private String fiscalCode;
    @Column(nullable = false)
    private Role role;
    private boolean blocked;
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

}
