package org.example.controller;

import org.example.model.Motorcycle;
import org.example.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

import java.util.List;

@Controller
public class HomeController {

    private final MotorcycleService motorcycleService;

    @Autowired
    public HomeController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Motorcycle> motorcycles = motorcycleService.getAllMotorcycles();

        model.addAttribute("page_title", "Motorcycles Rental Company");
        model.addAttribute("page", "../index");
        model.addAttribute("motorcycles", motorcycles);
        return "layouts/main"; // Используем шаблон, а он уже вставит home.jsp
    }
}