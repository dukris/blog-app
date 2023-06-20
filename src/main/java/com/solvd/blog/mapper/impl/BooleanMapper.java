package com.solvd.blog.mapper.impl;

import com.solvd.blog.mapper.Mapper;
import org.neo4j.driver.Record;
import org.springframework.stereotype.Component;

@Component
public class BooleanMapper implements Mapper<Boolean> {

    @Override
    public Boolean toEntity(final Record record) {
        return record.get(0).asBoolean();
    }

}
