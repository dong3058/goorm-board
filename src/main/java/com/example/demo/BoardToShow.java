package com.example.demo;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;


public class BoardToShow {
    private Long id;
    private String title;

    private LocalDateTime created_dt;

    public LocalDateTime getCreated_dt() {
        return created_dt;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BoardToShow(Long id, String title, LocalDateTime created_dt) {
        this.id = id;
        this.title = title;
        this.created_dt = created_dt;
    }
}
