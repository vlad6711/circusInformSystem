package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.models.Show;
import com.example.circusinfosystem.repositories.ShowRepository;
import com.example.circusinfosystem.repositories.TicketRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/shows")
public class AdminShowController {

    private final ShowRepository repo;
    private final TicketRepository ticketRepository;

    public AdminShowController(ShowRepository repo, TicketRepository ticketRepository) {
        this.repo = repo;
        this.ticketRepository = ticketRepository;
    }


    @GetMapping
    public String admin(Model model) {
        model.addAttribute("shows", repo.findAll());
        return "admin-shows";
    }

    @GetMapping("/new")
    public String form(Model model) {
        model.addAttribute("show", new Show());
        return "show-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Show show) {
        repo.save(show);
        return "redirect:/admin/shows";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id,
                         RedirectAttributes redirectAttributes) {

        if (ticketRepository.existsByShowId(id)) {
            redirectAttributes.addFlashAttribute(
                    "error",
                    "Нельзя удалить представление: на него уже куплены билеты"
            );
            return "redirect:/admin/shows";
        }

        repo.deleteById(id);
        redirectAttributes.addFlashAttribute(
                "success",
                "Представление успешно удалено"
        );

        return "redirect:/admin/shows";
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Show show = repo.findById(id).orElse(null);
        model.addAttribute("show", show);
        return "show-form";
    }

}
