package giacomo.bongiovanni.poptickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Seat {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   @Column(nullable = false,length = 30)
    private String name;
   @Column(nullable = false)
    private double price;
   @ManyToOne
   @JoinColumn(name = "sectionID",nullable = false)
    private Section section;
   @OneToMany(mappedBy = "seat")
    private List<Ticket>tickets;
}
