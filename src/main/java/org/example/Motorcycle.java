package org.example;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Motorcycle {
    private Long id;
    private String name;

    public Motorcycle(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}
