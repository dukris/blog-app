package com.solvd.blog.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.solvd.blog.error.JsonError;

import java.io.IOException;

public class ErrorSerializer extends JsonSerializer<JsonError> {

    @Override
    public void serialize(final JsonError value,
                          final JsonGenerator gen,
                          final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("message", value.message());
        gen.writeEndObject();
    }

}
