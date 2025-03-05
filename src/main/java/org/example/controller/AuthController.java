package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.model.Motorcycle;
import org.example.service.MotorcycleService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> store(@RequestParam String phoneNumber, HttpSession session) {
        if (phoneNumber.isBlank()) {
            return ResponseEntity.ok(Map.of("status", 0, "message", "Не указан телефон."));
        }

        User user = userService.getUserByPhoneNumber(phoneNumber)
                .orElseGet(() -> userService.createUser(new User(phoneNumber)));

        user.setEnterCode(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
        user.setEnterCodeExpiredAt(LocalDateTime.now().plusMinutes(5));
        userService.saveUser(user);

        session.setAttribute("userId", user.getId());

        return ResponseEntity.ok(Map.of("status", 1, "message", "ok"));
    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.removeAttribute("userId");
        return null;
    }
}