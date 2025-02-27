package org.example;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository = new UserRepository();

    public void addUser(String phoneNumber, BigDecimal balance, String enterCode, int enterCodeExpiredAt) throws SQLException {
        int now = (int) (System.currentTimeMillis() / 1000);
        User user = new User(null, phoneNumber, now, now, balance, enterCode, enterCodeExpiredAt);
        userRepository.createUser(user);
    }

    public void listUsers() throws SQLException {
        List<User> users = userRepository.getAllUsers();
        users.forEach(user -> System.out.println("ID: " + user.getId() + ", Phone: " + user.getPhoneNumber() + ", Balance: " + user.getBalance()));
    }

    public void getUser(Long id) throws SQLException {
        User user = userRepository.getUser(id);
        System.out.println("ID: " + user.getId() + ", Phone: " + user.getPhoneNumber() + ", Balance: " + user.getBalance());
    }

    public void updateUserBalance(Long id, BigDecimal newBalance) throws SQLException {
        userRepository.updateUserBalance(id, newBalance);
    }

    public void deleteUser(Long id) throws SQLException {
        userRepository.deleteUser(id);
    }
}
