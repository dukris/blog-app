package com.solvd.blog.fake;

import com.solvd.blog.model.Post;

import java.time.LocalDate;

public class FkPost implements Post {

    private static final Long ID = 1L;
    private static final String TITLE = "Title";
    private static final String CONTENT = "Content";
    private static final LocalDate DATE = LocalDate.now();

    @Override
    public Long id() {
        return ID;
    }

    @Override
    public String title() {
        return TITLE;
    }

    @Override
    public String content() {
        return CONTENT;
    }

    @Override
    public LocalDate date() {
        return DATE;
    }

}
