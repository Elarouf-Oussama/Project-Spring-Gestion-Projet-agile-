package com.agile_project_management.service;

import com.agile_project_management.model.Task;
import com.agile_project_management.model.enums.TaskStatus;

import java.util.List;

public interface ITaskService {
    Task createTask(Long userStoryId, Task task);
    Task updateTaskStatus(Long taskId, TaskStatus status);
    Task assignTaskToUser(Long taskId, Long userId);
    List<Task> getTasksByUserStory(Long userStoryId);
}