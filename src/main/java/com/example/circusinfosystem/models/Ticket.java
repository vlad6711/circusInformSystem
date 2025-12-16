package com.example.circusinfosystem.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    private LocalDateTime purchaseTime;

    public Ticket() {}

    public Ticket(User user, Show show) {
        this.user = user;
        this.show = show;
        this.purchaseTime = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Show getShow() { return show; }
    public LocalDateTime getPurchaseTime() { return purchaseTime; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setShow(Show show) { this.show = show; }
    public void setPurchaseTime(LocalDateTime purchaseTime) { this.purchaseTime = purchaseTime; }
}
