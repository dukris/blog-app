package com.solvd.blog.tx;

import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class TxUsers implements Users {

    private final Users users;

    /**
     * Constructor.
     *
     * @param users Users
     */
    public TxUsers(@Qualifier("neoUsers") final Users users) {
        this.users = users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> iterate() {
        return this.users.iterate();
    }

    @Override
    @Transactional(readOnly = true)
    public User user(final Long id) {
        return this.users.user(id);
    }

    @Override
    @Transactional
    public User add(final User user) {
        return this.users.add(user);
    }

    @Override
    @Transactional
    public User update(final User user) {
        return this.users.update(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean exists(final String email) {
        return this.users.exists(email);
    }

}
