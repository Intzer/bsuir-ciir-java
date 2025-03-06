package org.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals_durations")
public class RentalDuration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer days;

    @Column(nullable = false)
    private Integer cost;

    public RentalDuration() {}

    public Long getId() { return id; }
    public Integer getDays() { return days; }
    public void setDays(Integer days) { this.days = days; }
    public Integer getCost() { return cost; }
    public void setCost(Integer cost) { this.cost = cost; }
}
