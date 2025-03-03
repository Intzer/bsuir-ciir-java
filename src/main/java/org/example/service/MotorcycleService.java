package org.example.service;

import org.example.model.Motorcycle;
import org.example.repository.MotorcycleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleService {

    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public MotorcycleService(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public List<Motorcycle> getAllMotorcycles() {
        return motorcycleRepository.findAll();
    }

    public List<Motorcycle> getAllFreeMotorcycles() {
        return motorcycleRepository.getAllFreeMotorcycles();
    }

    public Optional<Motorcycle> getFreeMotorcycleById(Long id) {
        return motorcycleRepository.getFreeMotorcycleById(id);
    }
}