package com.solvd.blog.neo4j;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.blog.model.Post;
import com.solvd.blog.model.User;
import com.solvd.blog.serializer.UserSerializer;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
@JsonSerialize(using = UserSerializer.class)
public final class NeoUser implements User {

    private final Long id;
    private final String name;
    private final String email;
    private final List<Post> posts;

    @Override
    public Long id() {
        return this.id;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String email() {
        return this.email;
    }

    @Override
    public List<Post> posts() {
        return this.posts;
    }

}
