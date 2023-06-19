package com.solvd.blog.error;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.solvd.blog.serializer.ErrorSerializer;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonSerialize(using = ErrorSerializer.class)
public final class JsonError implements Error {

    private final String message;

    @Override
    public String message() {
        return this.message;
    }

}
