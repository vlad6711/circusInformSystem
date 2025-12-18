package com.example.circusinfosystem.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;

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

    @Min(value = 1, message = "Номер места должен быть положительным")
    @Column(name = "seat_number", nullable = false)
    private Integer seatNumber;

    private LocalDateTime purchaseTime;

    public Ticket() {
    }

    public Ticket(User user, Show show, Integer seatNumber) {
        this.user = user;
        this.show = show;
        this.seatNumber = seatNumber;
        this.purchaseTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public LocalDateTime getPurchaseTime() {
        return purchaseTime;
    }

    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public void setPurchaseTime(LocalDateTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }
}