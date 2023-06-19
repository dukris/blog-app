package com.solvd.blog.mapper.impl;

import com.solvd.blog.mapper.Mapper;
import com.solvd.blog.model.Post;
import com.solvd.blog.neo4j.NeoPost;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<Post> {

    @Override
    public Post toEntity(final Record record) {
        Node node = record.get("p").asNode();
        return new NeoPost(
                node.id(),
                node.get("title").asString(),
                node.get("content").asString(),
                node.get("date").asLocalDate()
        );
    }

}
