package org.example.repository;

import org.example.model.Rental;
import org.example.model.RentalDuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RentalDurationRepository extends JpaRepository<RentalDuration, Long> {

}
