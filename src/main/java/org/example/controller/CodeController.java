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
public class CodeController {

    private final UserService userService;

    @Autowired
    public CodeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/code")
    public String index(@RequestParam(value = "status", required = false) Integer status, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        model.addAttribute("status", status); // передаем status в модель
        model.addAttribute("page_title", "Enter code");
        model.addAttribute("page", "../code");
        return "layouts/main"; // Используем шаблон, а он уже вставит home.jsp
    }

    @PostMapping("/code")
    public String store(@RequestParam String code, String action, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            return "redirect:/auth";
        }

        User user = userOptional.get();

        if (action.equals("enter")) {
//            if (user.getEnterCodeExpiredAt().isBefore(LocalDateTime.now())) {
//                return "redirect:/code?status=2";
//            }
//
//            if (!user.getEnterCode().equals(code)) {
//                return "redirect:/code?status=1";
//            }

            session.setAttribute("isLogged", true);

            return "redirect:/cabinet";
        } else if (action.equals("resend")) {
            if (user.getEnterCodeExpiredAt().isAfter(LocalDateTime.now())) {
                return "redirect:/code?status=4";
            }

            user.setEnterCode(String.valueOf(ThreadLocalRandom.current().nextInt(100000, 999999)));
            user.setEnterCodeExpiredAt(LocalDateTime.now().plusMinutes(5));
            userService.saveUser(user);

            return "redirect:/code?status=5";
        }

        return "redirect:/code?error=3";
    }
}