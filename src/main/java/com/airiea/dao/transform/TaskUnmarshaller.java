package com.airiea.dao.transform;

import com.airiea.dao.orm.TaskRecord;
import com.airiea.model.resource.Task;

public class TaskUnmarshaller {
    public Task unmarshall(final TaskRecord taskRecord) {
        if (taskRecord == null) {
            return null;
        }
        return Task.builder()
                .taskId(taskRecord.getTaskId())
                .entityId(taskRecord.getEntityId())
                .agentName(taskRecord.getAgentName())
                .taskCount(taskRecord.getTaskCount())
                .taskInput(taskRecord.getTaskInput())
                .taskOutput(taskRecord.getTaskOutput())
                .status(taskRecord.getStatus())
                .errorCode(taskRecord.getErrorCode())
                .errorMessage(taskRecord.getErrorMessage())
                .updatedDate(taskRecord.getUpdatedDate())
                .createdDate(taskRecord.getCreatedDate())
                .build();
    }

    private static TaskUnmarshaller instance;

    public static TaskUnmarshaller getInstance() {
        if (instance == null)
            instance = new TaskUnmarshaller();
        return instance;
    }
}
