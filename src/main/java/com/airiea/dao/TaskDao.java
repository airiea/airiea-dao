package com.airiea.dao;

import com.airiea.model.resource.Task;

public interface TaskDao {
    Task getTaskById(String taskId);
}
