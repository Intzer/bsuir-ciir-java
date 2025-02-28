package org.example;

import org.example.DatabaseConnection;

import java.sql.SQLException;
import java.util.List;

public class RentalService {
    private final RentalRepository rentalRepository = new RentalRepository();

    public void rentMotorcycle(Long userId, Long motorcycleId, int hours) throws SQLException {
        rentalRepository.rentMotorcycle(userId, motorcycleId, hours);
    }

    public void listUserRentals(Long userId) throws SQLException {
        List<Rental> rentals = rentalRepository.listUserRentals(userId);
        rentals.forEach(rental -> System.out.println("ID: " + rental.getId() + ", rentalName: " + rental.getMotorcycleName() + ", expiredAt: " + rental.getExpiredAt()));
    }
}
