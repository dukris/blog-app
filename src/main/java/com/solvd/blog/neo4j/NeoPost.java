package com.solvd.blog.neo4j;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.blog.model.Post;
import com.solvd.blog.serializer.PostSerializer;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@JsonSerialize(using = PostSerializer.class)
public final class NeoPost implements Post {

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
