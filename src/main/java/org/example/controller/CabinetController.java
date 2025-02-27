package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CabinetController {

    private final UserService userService;

    @Autowired
    public CabinetController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/cabinet")
    public String index(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        model.addAttribute("page_title", "Cabinet");
        model.addAttribute("page", "../cabinet");
        return "layouts/main"; // Используем шаблон, а он уже вставит home.jsp
    }
}