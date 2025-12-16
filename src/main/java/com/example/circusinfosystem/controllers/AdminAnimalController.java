package com.example.circusinfosystem.controllers;

import com.example.circusinfosystem.models.Animal;
import com.example.circusinfosystem.repositories.AnimalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/animals")
public class AdminAnimalController {

    private final AnimalRepository animalRepository;

    public AdminAnimalController(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    @GetMapping
    public String animals(Model model) {
        model.addAttribute("animals", animalRepository.findAll());
        return "admin-animals";
    }

    @GetMapping("/new")
    public String newAnimal(Model model) {
        model.addAttribute("animal", new Animal());
        return "animal-form";
    }

    @PostMapping("/save")
    public String saveAnimal(@ModelAttribute Animal animal) {
        animalRepository.save(animal);
        return "redirect:/admin/animals";
    }

    @GetMapping("/edit/{id}")
    public String editAnimal(@PathVariable Long id, Model model) {
        model.addAttribute("animal", animalRepository.findById(id).orElseThrow());
        return "animal-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteAnimal(@PathVariable Long id) {
        animalRepository.deleteById(id);
        return "redirect:/admin/animals";
    }
}
