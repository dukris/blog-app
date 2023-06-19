package com.solvd.blog.request;

import com.solvd.blog.model.Post;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RequiredArgsConstructor
public final class RqPost implements Post {

    private final Long id;
    private final String title;
    private final String content;
    private final LocalDate date;

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String title() {
        return this.title;
    }

    @Override
    public String content() {
        return this.content;
    }

    @Override
    public LocalDate date() {
        return this.date;
    }
}
