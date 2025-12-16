package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.repositories.ShowRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {

    private final ShowRepository showRepository;

    public ScheduleController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping("/schedule")
    public String schedule(Model model) {
        model.addAttribute("shows", showRepository.findAll());
        return "schedule";
    }
}
