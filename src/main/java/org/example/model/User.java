package org.example.model;

import org.hibernate.Internal;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private Float balance;

    @Column(nullable = false)
    private String enterCode;

    @Column(nullable = false)
    private Integer enterCodeExpiredAt;

    @CreationTimestamp
    @Column(updatable = false) // Заполняется один раз при создании
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Конструкторы, геттеры и сеттеры
    public User() {}

    public Long getId() { return id; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public Float getBalance() { return balance; }
    public void setBalance(Float balance) { this.balance = balance; }
    public String getEnterCode() { return enterCode; }
    public void setEnterCode(String enterCode) { this.enterCode = User.this.enterCode; }
    public Integer getEnterCodeExpiredAt() { return enterCodeExpiredAt; }
    public void setEnterCodeExpiredAt(Integer enterCodeExpiredAt) { this.enterCodeExpiredAt = enterCodeExpiredAt; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
