package com.example.circusinfosystem.repositories;

import com.example.circusinfosystem.models.Ticket;
import com.example.circusinfosystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByUser(User user);

    boolean existsByShowId(Long showId);

    Optional<Ticket> findByIdAndUser(Long id, User user);

}
