package com.solvd.blog.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.solvd.blog.neo4j.NeoPost;

import java.io.IOException;

public class PostSerializer extends JsonSerializer<NeoPost> {

    @Override
    public void serialize(
            final NeoPost value,
            final JsonGenerator gen,
            final SerializerProvider serializers
    ) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", value.id());
        gen.writeStringField("title", value.title());
        gen.writeStringField("content", value.content());
        gen.writeObjectField("date", value.date());
        gen.writeEndObject();
    }

}
