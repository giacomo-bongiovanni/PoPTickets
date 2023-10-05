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
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,length = 30)
    private String name;
    @Column(nullable = false,length = 30)
    private String road;
    @Column(nullable = false,length = 30)
    private String city;
    @Column(nullable = false,length = 30)
    private String postalCode;
    @Column(nullable = false,length = 30)
    private String province;
    @OneToMany(mappedBy = "place")
    private List<Section>sections;
    @OneToMany(mappedBy = "place")
    private List<Event>events;
}
