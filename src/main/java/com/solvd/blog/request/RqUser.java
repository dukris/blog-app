package com.solvd.blog.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.blog.group.OnCreate;
import com.solvd.blog.group.OnUpdate;
import com.solvd.blog.model.Post;
import com.solvd.blog.model.User;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RqUser implements User {

    @Null(groups = OnCreate.class, message = "Id should be empty!")
    @NotNull(groups = OnUpdate.class, message = "Id should be filled!")
    private final Long id;
    private final String name;
    private final String email;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
