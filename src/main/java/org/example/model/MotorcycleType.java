package org.example.model;

import org.hibernate.Internal;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "motorcycles_types")
public class MotorcycleType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String image;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer engineVolume;

    @Column(nullable = false)
    private Integer maxSpeed;

    @Column(nullable = false)
    private Float fuelRate;

    // Конструкторы, геттеры и сеттеры
    public MotorcycleType() {}

    public Long getId() { return id; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getEngineVolume() { return engineVolume; }
    public void setEngineVolume(Integer engineVolume) { this.engineVolume = engineVolume; }
    public Integer getMaxSpeed() { return maxSpeed; }
    public void setMaxSpeed(Integer maxSpeed) { this.maxSpeed = maxSpeed; }
    public Float getFuelRate() { return fuelRate; }
    public void setFuelRate(Float fuelRate) { this.fuelRate = fuelRate; }
}
