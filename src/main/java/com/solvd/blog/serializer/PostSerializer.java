package com.solvd.blog.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.solvd.blog.neo4j.NeoPost;

import java.io.IOException;

public class PostSerializer extends JsonSerializer<NeoPost> {

    @Override
    public void serialize(NeoPost value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.id());
        gen.writeStringField("title", value.title());
        gen.writeStringField("content", value.content());
        gen.writeObjectField("date", value.date());
        gen.writeEndObject();
    }

}
