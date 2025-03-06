package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CodeController {

    private final UserService userService;

    @Autowired
    public CodeController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/code")
    public ResponseEntity<Map<String, Object>> store(@RequestParam String code, String action, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }

        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(401).body(Map.of("status", 0, "message", "Не авторизован."));
        }

        User user = userOptional.get();

        if (action.equals("enter")) {
            if (user.getEnterCodeExpiredAt().isBefore(LocalDateTime.now())) {
                return ResponseEntity.ok().body(Map.of("status", 0, "message", "Время действия кода вышло."));
            }

            if (!user.getEnterCode().equals(code)) {
                return ResponseEntity.ok().body(Map.of("status", 0, "message", "Неверный код."));
            }

            session.setAttribute("isLogged", true);

            return ResponseEntity.ok().body(Map.of("status", 1, "message", "Авторизованы."));
        } else if (action.equals("resend")) {
            if (user.getEnterCodeExpiredAt().isAfter(LocalDateTime.now())) {
                return ResponseEntity.ok().body(Map.of("status", 0, "message", "Время действия кода ещё не вышло."));
            }

            user.setEnterCode(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
            user.setEnterCodeExpiredAt(LocalDateTime.now().plusMinutes(5));
            userService.saveUser(user);

            return ResponseEntity.ok().body(Map.of("status", 1, "message", "Новый код был отправлен."));        }

        return ResponseEntity.ok().body(Map.of("status", 0, "message", "Неизвестное действие."));
    }
}