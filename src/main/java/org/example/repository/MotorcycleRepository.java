package org.example.repository;

import org.example.model.Motorcycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MotorcycleRepository extends JpaRepository<Motorcycle, Long> {
    @Query("""
        SELECT m FROM Motorcycle m 
        LEFT JOIN Rental r ON m.id = r.motorcycle.id
            WHERE r.id IS NULL\s
        OR (r.expiredAt = (
            SELECT MAX(r2.expiredAt) FROM Rental r2 WHERE r2.motorcycle.id = m.id
        ) AND r.expiredAt < CURRENT_TIMESTAMP)
            ORDER BY COALESCE(r.expiredAt, CURRENT_TIMESTAMP) DESC
    """)
    List<Motorcycle> getAllFreeMotorcycles();

    @Query("""
        SELECT m FROM Motorcycle m 
        LEFT JOIN Rental r ON m.id = r.motorcycle.id
        WHERE m.id = :id 
            AND (r.id IS NULL 
            OR (r.expiredAt = (
                SELECT MAX(r2.expiredAt) FROM Rental r2 WHERE r2.motorcycle.id = m.id
            ) AND r.expiredAt < CURRENT_TIMESTAMP))
    """)
    Optional<Motorcycle> getFreeMotorcycleById(Long id);
}
