package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.repositories.ArtistRepository;
import com.example.circusinfosystem.repositories.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArtistPageController {

    private final ArtistRepository artistRepository;
    private final AnimalRepository animalRepository;

    public ArtistPageController(ArtistRepository artistRepository,
                                AnimalRepository animalRepository) {
        this.artistRepository = artistRepository;
        this.animalRepository = animalRepository;
    }

    /**
     * Пользовательская страница "Артисты"
     * URL: /artists
     */
    @GetMapping("/artists")
    public String artistsPage(Model model) {
        model.addAttribute("artists", artistRepository.findAll());
        model.addAttribute("animals", animalRepository.findAll());
        return "artists"; // artists.html
    }
}
