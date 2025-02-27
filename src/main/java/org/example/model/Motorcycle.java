package org.example.model;

import org.hibernate.Internal;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "motorcycles")
public class Motorcycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String vin;

    @CreationTimestamp
    @Column(updatable = false) // Заполняется один раз при создании
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false) // Внешний ключ на `motorcycle_types`
    private MotorcycleType type;

    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false) // Внешний ключ на `motorcycle_types`
    private MotorcycleColor color;

    // Конструкторы, геттеры и сеттеры
    public Motorcycle() {}

    public Long getId() { return id; }
    public String getVin() { return vin; }
    public void setVin(String vin) { this.vin = vin; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public MotorcycleType getType() {
        return type;
    }

    public void setType(MotorcycleType type) {
        this.type = type;
    }

    public MotorcycleColor getColor() {
        return color;
    }

    public void setColor(MotorcycleColor color) {
        this.color = color;
    }
}
