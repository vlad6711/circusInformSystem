package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.models.Artist;
import com.example.circusinfosystem.repositories.ArtistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/artists")
public class AdminArtistController {

    private final ArtistRepository artistRepository;

    public AdminArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @GetMapping
    public String artists(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        return "admin-artists";
    }

    @GetMapping("/new")
    public String newArtist(Model model) {
        model.addAttribute("artist", new Artist());
        return "artist-form";
    }

    @PostMapping("/save")
    public String saveArtist(@ModelAttribute Artist artist) {
        artistRepository.save(artist);
        return "redirect:/admin/artists";
    }

    @GetMapping("/edit/{id}")
    public String editArtist(@PathVariable Long id, Model model) {
        model.addAttribute("artist", artistRepository.findById(id).orElseThrow());
        return "artist-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable Long id) {
        artistRepository.deleteById(id);
        return "redirect:/admin/artists";
    }
}
