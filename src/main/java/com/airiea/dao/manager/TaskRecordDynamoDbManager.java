package com.airiea.dao.manager;

import com.airiea.dao.orm.TaskRecord;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
import java.util.Objects;

public class TaskRecordDynamoDbManager extends AbstractDynamoDbManager<TaskRecord> {
    private static final int QUERY_LIMIT = 100;
    private final DynamoDBMapper mapper;

    public TaskRecordDynamoDbManager(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
    }

    public void create(final TaskRecord item) {
        mapper.save(item);
    }

    public void update(final TaskRecord item) {
        mapper.save(item);
    }

    public void remove(final TaskRecord item) {
        mapper.delete(item);
    }

    public TaskRecord get(final TaskRecord item) {
        return mapper.load(item);
    }
    public TaskRecord getTaskRecordById(final String taskId) {
        return mapper.load(TaskRecord.class, taskId);
    }

    public List<TaskRecord> queryWithLimit(DynamoDBQueryExpression<TaskRecord> expression) {
        validateOnQuery(expression, QUERY_LIMIT);
        return mapper.query(TaskRecord.class, expression);
    }

}
