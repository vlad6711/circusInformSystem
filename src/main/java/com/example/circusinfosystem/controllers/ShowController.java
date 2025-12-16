package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.repositories.ShowRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ShowController {

    private final ShowRepository showRepository;

    public ShowController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping("/shows")
    public String shows(Model model) {
        model.addAttribute("shows", showRepository.findAll());
        return "shows";
    }

    @GetMapping("/shows/{id}")
    public String showPage(@PathVariable Long id, Model model) {
        model.addAttribute("show", showRepository.findById(id).orElse(null));
        return "show-page";
    }
}
