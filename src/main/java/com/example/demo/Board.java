package com.example.demo;

import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "maintext")
    private String maintext;

    @Column(name = "create_dt")
    private LocalDateTime create_dt;

    @Column(name="delete_check")
    private String deleted;

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    @OneToMany(mappedBy = "board",fetch = FetchType.LAZY)
    private List<Comment> commentList = new ArrayList<>();
    //내부에 에 관련된 getter setter 가 존재한다면 테이블에서 불러오고 나서 객체에 매핑할떄
    //값을 집어넣을려고 comment를 다시 호출한다.즉 자동으로 다시호출하는대 왜이런지는 모르겟다?
    //우선 알아만두자.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }



}



