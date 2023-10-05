package giacomo.bongiovanni.poptickets.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventDate implements Comparable<EventDate>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime endDate;
    @ManyToOne
    @JoinColumn(name = "eventID",nullable = false)
    private Event event;
    //mette in ordine crescente i miei eventi
    //l'ordine crescente viene dato confrontanto a due a due gli oggetti
    //chiamo il metodo su uno dei due e lo confronto con l'altro
    //se ritorno un valore negativo vuol dire che il parametro va prima di quello su cui chiamo il metodo
    //se ritorno un valore positivo vuol dire che quello su cui chiamo il metodo va prima del parametro
    //se ritorno zero sono uguali
    @Override
    public int compareTo(EventDate o) {
        int compare=startDate.compareTo(o.startDate);
        if(compare==0)compare=endDate.compareTo(o.endDate);
        if(compare==0)compare=event.getName().compareToIgnoreCase(o.event.getName());
        return compare;
    }
}
