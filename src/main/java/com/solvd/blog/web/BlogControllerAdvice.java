package com.solvd.blog.web;

import com.solvd.blog.error.Error;
import com.solvd.blog.error.JsonError;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.exceptions.NoSuchRecordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class BlogControllerAdvice {

    @ExceptionHandler(NoSuchRecordException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error handle(
            final NoSuchRecordException ex) {
        return new JsonError(
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<Error> handle(
            final MethodArgumentNotValidException ex) {
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.add(
                                new JsonError(error.getDefaultMessage())
                        )
                );
        return errors;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public JsonError handleOtherExceptions(final Exception ex) {
        log.error(ex.getMessage(), ex);
        return new JsonError("Please, try later!");
    }

}
