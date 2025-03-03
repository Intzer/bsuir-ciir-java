package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.Motorcycle;
import org.example.model.Rental;
import org.example.model.User;
import org.example.repository.RentalRepository;
import org.example.service.MotorcycleService;
import org.example.service.RentalService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class CabinetController {

    private final UserService userService;
    private final MotorcycleService motorcycleService;
    private final RentalService rentalService;

    @Autowired
    public CabinetController(UserService userService, RentalService rentalService, MotorcycleService motorcycleService, RentalRepository rentalRepository) {
        this.userService = userService;
        this.rentalService = rentalService;
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/cabinet")
    public String cabinetIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        Long userId = (Long) session.getAttribute("userId");
        Optional<User> userOptional = userService.getUserById(userId);
        User user = userOptional.get();

        model.addAttribute("page_title", "Cabinet");
        model.addAttribute("page", "../cabinet");
        model.addAttribute("balance", user.getBalance());
        model.addAttribute("phoneNumber", user.getPhoneNumber());
        model.addAttribute("createdAt", user.getCreatedAt());

        return "layouts/main";
    }

    @GetMapping("/active")
    public String activeIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        Long userId = (Long) session.getAttribute("userId");
        List<Rental> rentals = rentalService.getUserRentals(userId);

        model.addAttribute("page_title", "Active");
        model.addAttribute("page", "../active");
        model.addAttribute("rentals", rentals);

        return "layouts/main";
    }

    @GetMapping("/history")
    public String historyIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        Long userId = (Long) session.getAttribute("userId");
        List<Rental> historyRentals = rentalService.getUserHistoryRentals(userId);

        model.addAttribute("page_title", "History");
        model.addAttribute("page", "../history");
        model.addAttribute("historyRentals", historyRentals);

        return "layouts/main";
    }

    @GetMapping("/rent")
    public String rentIndex(Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        List<Motorcycle> motorcycles = motorcycleService.getAllFreeMotorcycles();

        model.addAttribute("page_title", "Rent");
        model.addAttribute("page", "../rent");
        model.addAttribute("motorcycles", motorcycles);

        return "layouts/main";
    }

    @PostMapping("/rent")
    public String rentStore(@RequestParam Long motorcycleId, Model model, HttpSession session) {
        Boolean isLogged = (Boolean) session.getAttribute("isLogged");
        if (isLogged == null || !isLogged) {
            return "redirect:/auth"; // Если нет ID, отправляем снова на авторизацию
        }

        Long userId = (Long) session.getAttribute("userId");
        Optional<User> userOptional = userService.getUserById(userId);
        if (!userOptional.isPresent()) {
            return "redirect:/auth";
        }

        User user = userOptional.get();

        Optional<Motorcycle> motorcycleOptional = motorcycleService.getFreeMotorcycleById(motorcycleId);
        if (!motorcycleOptional.isPresent()) {
            return "redirect:/rent";
        }

        Motorcycle motorcycle = motorcycleOptional.get();
        rentalService.addRental(user, motorcycle);

        return "redirect:/active";
    }
}