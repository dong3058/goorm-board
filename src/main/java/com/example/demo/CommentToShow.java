package com.example.demo;

import java.time.LocalDateTime;

public class CommentToShow {

    private Long id;
    private String maintext;
    private LocalDateTime create_at;


    public CommentToShow(Long id, String maintext, LocalDateTime create_at) {
        this.id = id;
        this.maintext = maintext;
        this.create_at = create_at;
    }

    public Long getId() {
        return id;
    }

    public String getMaintext() {
        return maintext;
    }

    public LocalDateTime getCreate_at() {
        return create_at;
    }
}
