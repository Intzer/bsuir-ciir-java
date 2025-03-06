package org.example.service;

import org.example.model.Motorcycle;
import org.example.model.Rental;
import org.example.model.RentalDuration;
import org.example.model.User;
import org.example.repository.MotorcycleRepository;
import org.example.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;

    @Autowired
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public List<Rental> getUserRentals(Long userId) {
        return rentalRepository.findByUserIdAndExpiredAtAfter(userId, LocalDateTime.now());
    }

    public List<Rental> getUserHistoryRentals(Long userId) {
        return rentalRepository.findByUserIdAndExpiredAtBefore(userId, LocalDateTime.now());
    }

    public Rental addRental(User user, Motorcycle motorcycle, RentalDuration rentalDuration) {
        Rental rental = new Rental();
        rental.setUser(user);
        rental.setMotorcycle(motorcycle);
        rental.setExpiredAt(LocalDateTime.now().plusDays(rentalDuration.getDays()));
        return rentalRepository.save(rental);
    }
}