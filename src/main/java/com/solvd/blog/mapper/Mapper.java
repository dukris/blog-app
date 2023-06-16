package com.solvd.blog.mapper;

import org.neo4j.driver.Record;

public interface Mapper<T> {

    T toEntity(Record record);

}
