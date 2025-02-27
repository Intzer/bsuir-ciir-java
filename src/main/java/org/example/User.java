package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class User {
    private Long id;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private BigDecimal balance;
    private String enterCode;
    private LocalDateTime enterCodeExpiredAt;

    public User(Long id, String phoneNumber, LocalDateTime createdAt, LocalDateTime updatedAt, BigDecimal balance, String enterCode, LocalDateTime enterCodeExpiredAt) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.balance = balance;
        this.enterCode = enterCode;
        this.enterCodeExpiredAt = enterCodeExpiredAt;
    }

    public Long getId() { return id; }
    public String getPhoneNumber() { return phoneNumber; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public BigDecimal getBalance() { return balance; }
    public String getEnterCode() { return enterCode; }
    public LocalDateTime getEnterCodeExpiredAt() { return enterCodeExpiredAt; }
}
