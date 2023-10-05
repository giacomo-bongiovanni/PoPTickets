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
public class Section extends  DefaultEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 30)
    private String name;
    @ManyToOne
    @JoinColumn(name = "placeId",nullable = false)
    private Place place;
    @OneToMany(mappedBy = "section")
    private List<Seat>seats;
}
