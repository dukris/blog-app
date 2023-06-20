package com.solvd.blog.model;

import java.util.List;

public interface Users {

    /**
     * Get all users.
     *
     * @return List of users
     */
    List<User> iterate();

    /**
     * Get user by id.
     *
     * @param id Id
     * @return User
     */
    User user(Long id);

    /**
     * Add new user.
     *
     * @param user User
     * @return User
     */
    User add(User user);

    /**
     * Update user.
     *
     * @param user User
     * @return User
     */
    User update(User user);

    /**
     * Is exists by email.
     *
     * @param email Email
     * @return Boolean
     */
    Boolean exists(String email);

}
