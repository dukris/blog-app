package com.solvd.blog.neo4j;

import com.solvd.blog.mapper.impl.PostMapper;
import com.solvd.blog.model.Post;
import com.solvd.blog.model.Posts;
import lombok.RequiredArgsConstructor;
import org.neo4j.driver.Driver;
import org.neo4j.driver.Query;
import org.neo4j.driver.Session;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * @author Aliaksei Bialiauski (abialiuski.dev@gmail.com)
 * @since 0.0.0
 */
@Component
@RequiredArgsConstructor
public class NjPosts implements Posts {

    private final Driver driver;
    private final PostMapper mapper;

    @Override
    public Post add(final Post post, final Long user) {
        try (Session session = this.driver.session()) {
            return this.mapper.toEntity(
                    session.run(
                            new Query(
                                    "CREATE (p:Post {title:$title,"
                                            + " content:$content, date:$date})"
                                            + " WITH p"
                                            + " MATCH (u:User)"
                                            + " WHERE ID(u)=$user "
                                            + " CREATE "
                                            + "(p)-[r:MAINTAINED]->(u)"
                                            + " RETURN p",
                                    Map.of(
                                            "title", post.title(),
                                            "content", post.content(),
                                            "date", LocalDate.now(),
                                            "user", user
                                    )
                            )
                    ).single()
            );
        }
    }

    @Override
    public Post post(final Long id) {
        try (Session session = this.driver.session()) {
            return this.mapper.toEntity(
                    session.run(
                            new Query(
                                    "MATCH (p:Post) WHERE ID(p)=$id RETURN p ",
                                    Map.of("id", id)
                            )
                    ).single()
            );
        }
    }

    @Override
    public List<Post> iterate(final Long user) {
        try (Session session = this.driver.session()) {
            return session.run(
                    new Query(
                            "MATCH (p:Post)-[r:MAINTAINED]->(u:User)"
                                    + " WHERE ID(u)=$user RETURN p",
                            Map.of("user", user)
                    )
            ).list(this.mapper::toEntity);
        }
    }

    @Override
    public List<Post> iterate() {
        try (Session session = this.driver.session()) {
            return session.run(new Query("MATCH (p:Post) RETURN p"))
                    .list(this.mapper::toEntity);
        }
    }
}
