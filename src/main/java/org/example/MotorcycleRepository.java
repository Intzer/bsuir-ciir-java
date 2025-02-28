package org.example;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MotorcycleRepository {
    public List<Motorcycle> getFree() throws SQLException {
        List<Motorcycle> motorcycles = new ArrayList<>();
        String sql = """
            SELECT DISTINCT m.id, motorcycles_types.name 
            FROM motorcycles m
            LEFT JOIN rentals r ON m.id = r.motorcycle_id
            JOIN motorcycles_types ON m.type_id = motorcycles_types.id
            WHERE r.expired_at IS NULL OR r.expired_at <= NOW()
        """;
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                motorcycles.add(new Motorcycle(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        }
        return motorcycles;
    }

    public Motorcycle get(Long id) throws SQLException {
        String sql = "SELECT * FROM motorcycles WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Motorcycle(
                        rs.getLong("id"),
                        rs.getString("name")
                );
            }
        }

        return null;
    }
}
