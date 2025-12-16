package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.models.*;
import com.example.circusinfosystem.repositories.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class TicketController {

    private final TicketRepository ticketRepository;
    private final ShowRepository showRepository;

    public TicketController(TicketRepository ticketRepository, ShowRepository showRepository) {
        this.ticketRepository = ticketRepository;
        this.showRepository = showRepository;
    }

    @PostMapping("/tickets/buy/{showId}")
    public String buyTicket(@PathVariable Long showId, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Шоу не найдено"));

        Ticket ticket = new Ticket(user, show);
        ticketRepository.save(ticket);

        return "redirect:/profile";
    }

    @PostMapping("/tickets/refund/{ticketId}")
    public String refundTicket(@PathVariable Long ticketId, HttpSession session) {

        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Билет не найден"));

        // 🔒 защита: можно сдавать только свой билет
        if (!ticket.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Нет прав на сдачу этого билета");
        }

        ticketRepository.delete(ticket);

        return "redirect:/profile";
    }
}
