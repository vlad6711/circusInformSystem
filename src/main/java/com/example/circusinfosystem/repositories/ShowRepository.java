package com.example.circusinfosystem.repositories;

import com.example.circusinfosystem.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<Show, Long> {
}
