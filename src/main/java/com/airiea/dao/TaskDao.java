package com.airiea.dao;

import com.airiea.model.resource.Task;

import java.util.List;

public interface TaskDao {
    Task getTaskById(String taskId);
    List<Task> getTaskListByEntityId(String entityId);
}
