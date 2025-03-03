package org.example.repository;

import org.example.model.Motorcycle;
import org.example.model.Rental;
import org.example.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUserIdAndExpiredAtAfter(Long userId, LocalDateTime now);
    List<Rental> findByUserIdAndExpiredAtBefore(Long userId, LocalDateTime now);
}
