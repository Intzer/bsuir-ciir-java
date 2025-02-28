package org.example;

import java.time.LocalDateTime;

public class Rental {
    private Long id;
    private Long userId;
    private Long motorcycleId;
    private String motorcycleName;
    private LocalDateTime expiredAt;

    public Rental(Long id, Long userId, String motorcycleName, LocalDateTime expiredAt) {
        this.id = id;
        this.userId = userId;
        this.motorcycleId = motorcycleId;
        this.motorcycleName = motorcycleName;
        this.expiredAt = expiredAt;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public Long getMotorcycleId() { return motorcycleId; }
    public String getMotorcycleName() { return motorcycleName; }
    public LocalDateTime getExpiredAt() { return expiredAt; }
}
