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
public class Event extends DefaultEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false,unique = true,length = 30)
    private String name;
    @Lob
    @Column(length = 512)
    private String description;
    @ManyToOne
    @JoinColumn(name = "categoryID",nullable = false)
    private Category category;
    @ManyToOne
    @JoinColumn(name = "placeId",nullable = false)
    private Place place;
    @OneToMany(mappedBy = "event")
    private List<EventDate> eventDates;

}
