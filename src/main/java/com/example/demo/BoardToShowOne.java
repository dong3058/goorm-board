package com.example.demo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
@Data
public class BoardToShowOne {

    private String title;
    private String maintext;
    private LocalDateTime create_at;

    private List<CommentToShow> commentlist;


    public BoardToShowOne(String title, String maintext, LocalDateTime create_at, List<CommentToShow> commentlist) {
        this.title = title;
        this.maintext = maintext;
        this.create_at = create_at;
        this.commentlist = commentlist;
    }


}
