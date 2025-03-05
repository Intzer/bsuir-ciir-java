package org.example.controller;

import org.example.model.Motorcycle;
import org.example.service.MotorcycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class HomeController {

    private final MotorcycleService motorcycleService;

    @Autowired
    public HomeController(MotorcycleService motorcycleService) {
        this.motorcycleService = motorcycleService;
    }

    @GetMapping("/api/motorcycles")
    @ResponseBody // Возвращаем JSON
    public List<Motorcycle> getMotorcycles() {
        return motorcycleService.getAllMotorcycles();
    }
}