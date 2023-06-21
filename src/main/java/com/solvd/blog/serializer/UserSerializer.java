package com.solvd.blog.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.solvd.blog.neo4j.NjUser;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<NjUser> {

    @Override
    public void serialize(
            final NjUser value,
            final JsonGenerator gen,
            final SerializerProvider serializers
    ) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.id());
        gen.writeStringField("name", value.name());
        gen.writeStringField("email", value.email());
        gen.writeFieldName("posts");
        gen.writeObject(value.posts());
        gen.writeEndObject();
    }

}
