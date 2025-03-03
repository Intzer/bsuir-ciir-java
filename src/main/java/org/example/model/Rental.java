package org.example.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false) // Заполняется один раз при создании
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Внешний ключ на `users`
    private User user;

    @ManyToOne
    @JoinColumn(name = "motorcycle_id", nullable = false) // Внешний ключ на `motorcycles`
    private Motorcycle motorcycle;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    public Rental() {}

    public Long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
    public void setExpiredAt(LocalDateTime expiredAt) { this.expiredAt = expiredAt; }
    public Motorcycle getMotorcycle() {
        return motorcycle;
    }
    public void setMotorcycle(Motorcycle motorcycle) {
        this.motorcycle = motorcycle;
    }
    public User getUser() {return user; }
    public void setUser(User user) {
        this.user = user;
    }
}
