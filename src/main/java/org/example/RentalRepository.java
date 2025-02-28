package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RentalRepository {
    public void rentMotorcycle(Long userId, Long motorcycleId, int hours) throws SQLException {
        String sql = "INSERT INTO rentals (user_id, motorcycle_id, created_at, expired_at) " +
                     "VALUES (?, ?, NOW(), NOW() + INTERVAL '" + hours + " hours')";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setLong(1, userId);
            statement.setLong(2, motorcycleId);
            statement.executeUpdate();
            System.out.println("Мотоцикл успешно арендован.");
        }
    }

    public List<Rental> listUserRentals(Long userId) throws SQLException {
        List <Rental> rentals = new ArrayList<>();

        String sql = "SELECT r.id, r.expired_at, m.id as motorcycleId, motorcycles_types.name FROM rentals r JOIN motorcycles m ON r.motorcycle_id = m.id JOIN motorcycles_types ON m.type_id = motorcycles_types.id WHERE r.user_id = ?";
        try (PreparedStatement statement = DatabaseConnection.getConnection().prepareStatement(sql)) {
            statement.setLong(1, userId);
            ResultSet rs = statement.executeQuery();
            System.out.println("Арендованные мотоциклы:");
            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getLong("id"),
                        rs.getLong("motorcycleId"),
                        rs.getString("name"),
                        rs.getObject("expired_at", LocalDateTime.class)
                ));
            }
        }

        return rentals;
    }
}
