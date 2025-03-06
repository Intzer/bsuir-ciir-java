package org.example.service;

import org.example.model.Motorcycle;
import org.example.model.Rental;
import org.example.model.RentalDuration;
import org.example.model.User;
import org.example.repository.RentalDurationRepository;
import org.example.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RentalDurationService {

    private final RentalRepository rentalRepository;
    private final RentalDurationRepository rentalDurationRepository;

    @Autowired
    public RentalDurationService(RentalRepository rentalRepository, RentalDurationRepository rentalDurationRepository) {
        this.rentalRepository = rentalRepository;
        this.rentalDurationRepository = rentalDurationRepository;
    }

    public List<RentalDuration> findAll() {
        return rentalDurationRepository.findAll();
    }

    public Optional<RentalDuration> findById(Long id) {
        return rentalDurationRepository.findById(id);
    }
}