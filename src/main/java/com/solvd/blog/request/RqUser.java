package com.solvd.blog.request;

import com.solvd.blog.model.Post;
import com.solvd.blog.model.User;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RqUser implements User {

    private final String id;
    private final String name;
    private final String email;
    private final List<Post> posts;

    @Override
    public String id() {
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
