package com.example.demo;

import java.util.Comparator;

public class CommentCompare implements Comparator<CommentToShow> {

    @Override
    public int compare(CommentToShow o1, CommentToShow o2) {
        if (o1.getCreate_at().isAfter(o2.getCreate_at())) {
            return 1;
        } else {
            return -1;

        }
    }
}
