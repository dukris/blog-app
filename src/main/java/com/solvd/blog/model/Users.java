package com.solvd.blog.model;

import java.util.List;

public interface Users {

    List<User> iterate();

    User user(Long id);

    User add(User user);

    User update(User user);

}
