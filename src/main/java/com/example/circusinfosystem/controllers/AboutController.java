package com.example.circusinfosystem.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping("/about")
    public String about(Model model, HttpSession session) {
        Object user = session.getAttribute("user");
        model.addAttribute("user", user);

        model.addAttribute("title", "Об авторе");

        return "about";
    }
}