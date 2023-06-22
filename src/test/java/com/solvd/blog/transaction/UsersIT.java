package com.solvd.blog.transaction;

import com.solvd.blog.fake.FkUser;
import com.solvd.blog.model.User;
import com.solvd.blog.model.Users;
import integration.Neo4jIntegration;
import integration.PropertyOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SuppressWarnings("JTCOP.RuleAllTestsHaveProductionClass")
public class UsersIT extends Neo4jIntegration {

    @Autowired
    @Qualifier("txUsers")
    private Users users;

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        new PropertyOf(
                registry,
                "spring.neo4j.uri",
                container.getBoltUrl()
        ).set();
    }

    @Test
    public void verifiesIterate() {
        Assertions.assertNotNull(
                this.users.iterate(),
                "List of users is empty"
        );
    }

    @Test
    public void verifiesUser() {
        User user = new FkUser("Name", "Email");
        User result = this.users.user(user.id());
        Assertions.assertEquals(
                user.name(),
                result.name(),
                "Names are not equal"
        );
        Assertions.assertEquals(
                user.email(),
                result.email(),
                "Emails are not equal"
        );
    }

    @Test
    public void verifiesUserThrowsNoSuchRecordException() {
        Assertions.assertThrows(
                NoSuchRecordException.class,
                () -> this.users.user(5L),
                "Expected NoSuchRecordException"
        );
    }

    @Test
    public void verifiesAdd() {
        User user = new FkUser("Name", "New email");
        User result = this.users.add(user);
        Assertions.assertEquals(
                user.name(),
                result.name(),
                "Names are not equal"
        );
        Assertions.assertEquals(
                user.email(),
                result.email(),
                "Emails are not equal"
        );
    }

    @Test
    public void verifiesUpdate() {
        User user = new FkUser("Updated name", "Email");
        User result = this.users.update(user);
        Assertions.assertEquals(
                user.name(),
                result.name(),
                "Names are not equal"
        );
        Assertions.assertEquals(
                user.email(),
                result.email(),
                "Emails are not equal"
        );
    }

    @Test
    public void verifiesExists() {
        Assertions.assertTrue(
                this.users.exists("Email"),
                "This email doesn't exist"
        );
    }

}
