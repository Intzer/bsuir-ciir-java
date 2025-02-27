package org.example;

import java.math.BigDecimal;

public class User {
    private Long id;
    private String phoneNumber;
    private int createdAt;
    private int updatedAt;
    private BigDecimal balance;
    private String enterCode;
    private int enterCodeExpiredAt;

    public User(Long id, String phoneNumber, int createdAt, int updatedAt, BigDecimal balance, String enterCode, int enterCodeExpiredAt) {
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
    public int getCreatedAt() { return createdAt; }
    public int getUpdatedAt() { return updatedAt; }
    public BigDecimal getBalance() { return balance; }
    public String getEnterCode() { return enterCode; }
    public int getEnterCodeExpiredAt() { return enterCodeExpiredAt; }
}
