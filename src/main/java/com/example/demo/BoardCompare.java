package com.example.demo;

import java.util.Comparator;

public class BoardCompare implements Comparator<BoardToShow> {

    @Override
    public int compare(BoardToShow o1, BoardToShow o2) {
        if (o1.getCreated_dt().isAfter(o2.getCreated_dt())) {
            return 1;
        } else {
            return -1;

        }
    }
}
