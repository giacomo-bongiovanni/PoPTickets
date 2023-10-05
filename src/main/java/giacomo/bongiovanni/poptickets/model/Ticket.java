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
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDateTime purchaseDate;
    @ManyToOne
    @JoinColumn(name = "seatId",nullable = false)
    private Seat seat;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}

