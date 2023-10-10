package com.airiea.dao.manager;

import com.airiea.dao.orm.TaskEntity;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;

public class TaskEntityDynamoDbManager extends AbstractDynamoDbManager<TaskEntity> {

    private static final int QUERY_LIMIT = 1000;
    private final DynamoDBMapper mapper;


    public TaskEntityDynamoDbManager(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
    }

    public void create(final TaskEntity item) {
        mapper.save(item);
    }

    public void update(final TaskEntity item) {
        mapper.save(item);
    }

    public void remove(final TaskEntity item) {
        mapper.delete(item);
    }

    public TaskEntity get(final TaskEntity item) {
        return mapper.load(item);
    }

    public List<TaskEntity> queryWithLimit(DynamoDBQueryExpression<TaskEntity> expression) {
        validateOnQuery(expression, QUERY_LIMIT);
        return mapper.query(TaskEntity.class, expression);
    }

}
