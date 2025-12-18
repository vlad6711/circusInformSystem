package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.models.Show;
import com.example.circusinfosystem.models.Ticket;
import com.example.circusinfosystem.models.User;
import com.example.circusinfosystem.repositories.ShowRepository;
import com.example.circusinfosystem.repositories.TicketRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SeatSelectionController {

    private final ShowRepository showRepository;
    private final TicketRepository ticketRepository;

    public SeatSelectionController(ShowRepository showRepository, TicketRepository ticketRepository) {
        this.showRepository = showRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping("/shows/{showId}/select-seat")
    public String selectSeat(@PathVariable Long showId, Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Шоу не найдено"));

        List<Ticket> soldTickets = ticketRepository.findByShowId(showId);
        Set<Integer> soldSeats = soldTickets.stream()
                .map(Ticket::getSeatNumber)
                .collect(Collectors.toSet());

        List<SeatInfo> seats = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            String sector = getSectorBySeat(i);
            seats.add(new SeatInfo(i, sector, soldSeats.contains(i)));
        }

        Map<String, List<SeatInfo>> seatsBySector = seats.stream()
                .collect(Collectors.groupingBy(SeatInfo::getSector));

        model.addAttribute("user", user);
        model.addAttribute("show", show);
        model.addAttribute("seatsBySector", seatsBySector);

        return "seat-selection";
    }

    private String getSectorBySeat(int seatNumber) {
        if (seatNumber <= 25) return "Сектор 1";
        if (seatNumber <= 50) return "Сектор 2";
        if (seatNumber <= 75) return "Сектор 3";
        return "Сектор 4";
    }

    public static class SeatInfo {
        private final int number;
        private final String sector;
        private final boolean isSold;

        public SeatInfo(int number, String sector, boolean isSold) {
            this.number = number;
            this.sector = sector;
            this.isSold = isSold;
        }

        public int getNumber() {
            return number;
        }

        public String getSector() {
            return sector;
        }

        public boolean isSold() {
            return isSold;
        }
    }
}