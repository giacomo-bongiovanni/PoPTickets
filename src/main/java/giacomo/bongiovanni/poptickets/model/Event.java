package giacomo.bongiovanni.poptickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @ManyToMany
    @JoinTable(
            name = "events_categories",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "placeId",nullable = false)
    private Place place;
    @OneToMany(mappedBy = "event")
    private List<EventDate> eventDates;

}
