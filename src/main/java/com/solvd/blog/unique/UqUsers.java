package com.solvd.blog.unique;

import com.solvd.blog.exception.ResourceAlreadyExistsException;
import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UqUsers implements Users {

    private final Users users;

    /**
     * Constructor.
     *
     * @param users Users
     */
    public UqUsers(@Qualifier("txUsers") final Users users) {
        this.users = users;
    }

    @Override
    public List<User> iterate() {
        return this.users.iterate();
    }

    @Override
    public User user(final Long id) {
        return this.users.user(id);
    }

    @Override
    public User add(final User user) {
        if (this.exists(user.email())) {
            throw new ResourceAlreadyExistsException(
                    "Email should be unique!"
            );
        }
        return this.users.add(user);
    }

    @Override
    public User update(final User user) {
        return this.users.update(user);
    }

    @Override
    public Boolean exists(final String email) {
        return this.users.exists(email);
    }

}
