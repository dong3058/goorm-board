package com.example.demo;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Table(name="comment")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    @Column(name = "maintext")
    private String maintext;

    @Column(name = "create_dt")
    private LocalDateTime create_dt;

    @Column(name = "delete_check")
    private String deleted;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public void setBoard(Board board) {
        this.board = board;
    }

    public String getMaintext() {
        return maintext;
    }

    public void setMaintext(String maintext) {
        this.maintext = maintext;
    }

    public LocalDateTime getCreate_dt() {
        return create_dt;
    }


    public void setCreate_dt(LocalDateTime create_dt) {
        this.create_dt = create_dt;
    }

}
