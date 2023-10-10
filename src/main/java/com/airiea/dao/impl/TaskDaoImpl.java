package com.airiea.dao.impl;

import com.airiea.dao.TaskDao;
import com.airiea.dao.orm.TaskRecord;
import com.airiea.dao.transform.TaskUnmarshaller;
import com.airiea.model.resource.Task;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import java.util.Objects;

public class TaskDaoImpl implements TaskDao {
    private final DynamoDBMapper mapper;
    private final TaskUnmarshaller taskUnmarshaller;

    public TaskDaoImpl(final DynamoDBMapper mapper) {
        this.mapper = Objects.requireNonNull(mapper, "Mapper cannot be null");
        this.taskUnmarshaller = TaskUnmarshaller.getInstance();
    }

    @Override
    public Task getTaskById(String taskId) {
        final TaskRecord taskRecord = mapper.load(TaskRecord.class, taskId);
        return taskUnmarshaller.unmarshall(taskRecord);
    }
}
