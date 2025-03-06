package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.Motorcycle;
import org.example.model.Rental;
import org.example.model.User;
import org.example.repository.RentalRepository;
import org.example.repository.UserRepository;
import org.example.service.MotorcycleService;
import org.example.service.RentalService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CabinetController {

    private final UserService userService;
    private final MotorcycleService motorcycleService;
    private final RentalService rentalService;
    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;

    @Autowired
    public CabinetController(UserService userService, RentalService rentalService, MotorcycleService motorcycleService, RentalRepository rentalRepository, UserRepository userRepository) {
        this.userService = userService;
        this.rentalService = rentalService;
        this.motorcycleService = motorcycleService;
        this.rentalRepository = rentalRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }

        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }

        User user = userOptional.get();

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("phone", user.getPhoneNumber());
        userInfo.put("balance", user.getBalance());

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/api/rentals")
    @ResponseBody
    public ResponseEntity<Object> activeIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return ResponseEntity.status(401).body("Не авторизован.");
        }

        Long userId = (Long) session.getAttribute("userId");
        List<Rental> rentals = rentalService.getUserRentals(userId);

        return ResponseEntity.ok(rentals);
    }

    @GetMapping("/api/history")
    public ResponseEntity<Object> historyIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return ResponseEntity.status(401).body("Не авторизован.");
        }

        Long userId = (Long) session.getAttribute("userId");
        List<Rental> historyRentals = rentalService.getUserHistoryRentals(userId);

        return ResponseEntity.ok(historyRentals);
    }

    @GetMapping("/api/freemotorcycles")
    public ResponseEntity<Object> rentIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return ResponseEntity.status(401).body("Не авторизован.");
        }

        List<Motorcycle> motorcycles = motorcycleService.getAllFreeMotorcycles();

        return ResponseEntity.ok(motorcycles);
    }

    @PostMapping("/rent")
    public ResponseEntity<Map<String, Object>> rentStore(@RequestParam Long motorcycleId, Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }

        Long userId = (Long) session.getAttribute("userId");
        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }
        User user = userOptional.get();

        Optional<Motorcycle> motorcycleOptional = motorcycleService.getFreeMotorcycleById(motorcycleId);
        if (!motorcycleOptional.isPresent()) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Данный мотоцикл уже недоступен."));
        }

        Motorcycle motorcycle = motorcycleOptional.get();
        rentalService.addRental(user, motorcycle);

        return ResponseEntity.ok().body(Map.of("status", 1, "message", "Арендовали.."));
    }

    @PostMapping("/deposit")
    public ResponseEntity<Map<String, Object>> depositStore(@RequestParam int amount, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }

        Long userId = (Long) session.getAttribute("userId");
        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }
        User user = userOptional.get();

        if (amount <= 0) {
            return ResponseEntity.ok().body(Map.of("status", 0, "message", "Сумма пополнения должна быть больше нуля."));
        }

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user);

        return ResponseEntity.ok().body(Map.of("status", 1, "message", "Баланс успешно пополнен."));
    }
}