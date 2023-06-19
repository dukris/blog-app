package com.solvd.blog.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.blog.model.Post;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RequiredArgsConstructor
public final class RqPost implements Post {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private final Long id;
    private final String title;
    private final String content;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
