package com.airiea.dao.impl;

import com.airiea.dao.TaskDao;
import com.airiea.dao.orm.TaskRecord;
import com.airiea.dao.transform.TaskUnmarshaller;
import com.airiea.model.resource.Task;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;
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
        return taskUnmarshaller.unmarshallFromTaskRecord(taskRecord);
    }

    @Override
    public List<Task> getTaskListByEntityId(String entityId) {
        TaskRecord gsiKeys = new TaskRecord();
        gsiKeys.setEntityId(entityId);

        DynamoDBQueryExpression<TaskRecord> queryExpression = new DynamoDBQueryExpression<TaskRecord>()
                .withIndexName("entity_id-agent_name")
                .withHashKeyValues(gsiKeys)
                .withConsistentRead(false);

        final List<TaskRecord> taskRecordList = mapper.query(TaskRecord.class, queryExpression);
        return taskUnmarshaller.unmarshallFromTaskRecordList(taskRecordList);
    }
}
