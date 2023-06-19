package com.solvd.blog.mapper;

import org.neo4j.driver.Record;

public interface Mapper<T> {

    /**
     * Map record to entity.
     *
     * @param record Record
     * @return T
     */
    T toEntity(Record record);

}
