package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.models.User;
import com.example.circusinfosystem.repositories.TicketRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

    private final TicketRepository ticketRepository;

    public ProfileController(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", user);

        model.addAttribute("tickets", ticketRepository.findByUser(user));

        return "profile";
    }
}
