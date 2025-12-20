package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.repositories.ShowRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ShowRepository showRepository;

    public HomeController(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        model.addAttribute("title", "Информационно-справочная система цирка");

        Object user = session.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("shows", showRepository.findAll());

        return "index";
    }
}
