package org.example;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO users (phone_number, created_at, updated_at, balance, enter_code, enter_code_expired_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getPhoneNumber());
            stmt.setInt(2, user.getCreatedAt());
            stmt.setInt(3, user.getUpdatedAt());
            stmt.setBigDecimal(4, user.getBalance());
            stmt.setString(5, user.getEnterCode());
            stmt.setInt(6, user.getEnterCodeExpiredAt());
            stmt.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getLong("id"),
                        rs.getString("phone_number"),
                        rs.getInt("created_at"),
                        rs.getInt("updated_at"),
                        rs.getBigDecimal("balance"),
                        rs.getString("enter_code"),
                        rs.getInt("enter_code_expired_at")
                ));
            }
        }
        return users;
    }

    public User getUser(Long id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getLong("id"),
                        rs.getString("phone_number"),
                        rs.getInt("created_at"),
                        rs.getInt("updated_at"),
                        rs.getBigDecimal("balance"),
                        rs.getString("enter_code"),
                        rs.getInt("enter_code_expired_at")
                );
            }
        }

        return null;
    }

    public void updateUserBalance(Long id, BigDecimal newBalance) throws SQLException {
        String sql = "UPDATE users SET balance = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, newBalance);
            stmt.setLong(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteUser(Long id) throws SQLException {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }
}
