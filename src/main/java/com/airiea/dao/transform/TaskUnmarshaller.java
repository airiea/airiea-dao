package com.airiea.dao.transform;

import com.airiea.dao.orm.TaskRecord;
import com.airiea.model.resource.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskUnmarshaller {
    public Task unmarshallFromTaskRecord(final TaskRecord taskRecord) {
        if (taskRecord == null) {
            return null;
        }
        return Task.builder()
                .taskId(taskRecord.getTaskId())
                .entityId(taskRecord.getEntityId())
                .agentName(taskRecord.getAgentName())
                .taskCount(taskRecord.getTaskCount())
                .textInput(taskRecord.getTextInput())
                .textOutput(taskRecord.getTextOutput())
                .embeddingInput(taskRecord.getEmbeddingInput())
                .embeddingOutput(taskRecord.getEmbeddingOutput())
                .status(taskRecord.getStatus())
                .errorCode(taskRecord.getErrorCode())
                .errorMessage(taskRecord.getErrorMessage())
                .updatedDate(taskRecord.getUpdatedDate())
                .createdDate(taskRecord.getCreatedDate())
                .build();
    }

    public List<Task> unmarshallFromTaskRecordList(final List<TaskRecord> taskRecordList) {
        List<Task> taskList = new ArrayList<>();
        for (TaskRecord taskRecord : taskRecordList) {
            taskList.add(unmarshallFromTaskRecord(taskRecord));
        }
        return taskList;
    }

    private static TaskUnmarshaller instance;

    public static TaskUnmarshaller getInstance() {
        if (instance == null)
            instance = new TaskUnmarshaller();
        return instance;
    }
}
