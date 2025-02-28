package org.example;

import org.example.Motorcycle;
import org.example.MotorcycleRepository;
import org.example.User;
import org.example.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class MotorcycleService {
    private final MotorcycleRepository motorcycleRepository = new MotorcycleRepository();

    public void listFreeMotorcycles() throws SQLException {
        List<Motorcycle> motorcycles = motorcycleRepository.getFree();
        motorcycles.forEach(motorcycle -> System.out.println("ID: " + motorcycle.getId() + ", Name: " + motorcycle.getName()));
    }
}
