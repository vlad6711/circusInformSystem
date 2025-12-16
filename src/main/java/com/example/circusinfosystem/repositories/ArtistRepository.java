package com.example.circusinfosystem.repositories;

import com.example.circusinfosystem.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
