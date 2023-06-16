package com.solvd.blog.model;

import io.github.eocqrs.eokson.Json;

import java.util.List;

public interface User {

    Long id();

    String name();

    String email();

    List<Post> posts();

}
