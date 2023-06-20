package com.solvd.blog.fake;

import com.solvd.blog.model.Post;
import com.solvd.blog.model.User;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class FkUser implements User {

    private static final Long ID = 0L;
    private static final List<Post> POSTS = new ArrayList<>();
    private static final String NAME = "Name";
    private final String email;

    @Override
    public Long id() {
        return ID;
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public String email() {
        return this.email;
    }

    @Override
    public List<Post> posts() {
        return POSTS;
    }

}
