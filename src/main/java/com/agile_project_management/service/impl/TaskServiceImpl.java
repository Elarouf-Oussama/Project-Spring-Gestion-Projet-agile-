package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.Task;
import com.agile_project_management.model.User;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.model.enums.TaskStatus;
import com.agile_project_management.repository.TaskRepository;
import com.agile_project_management.repository.UserRepository;
import com.agile_project_management.repository.UserStoryRepository;
import com.agile_project_management.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private final TaskRepository taskRepository;
    private final UserStoryRepository userStoryRepository;
    private final UserRepository userRepository;

    @Override
    public Task createTask(Long userStoryId, Task task) {
        UserStory story = userStoryRepository.findById(userStoryId)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory not found"));

        task.setUserStory(story);
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TO_DO);
        }
        return taskRepository.save(task);
    }

    @Override
    public Task updateTaskStatus(Long taskId, TaskStatus status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    public Task assignTaskToUser(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        task.setAssignee(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getTasksByUserStory(Long userStoryId) {
        return taskRepository.findByUserStoryId(userStoryId);
    }
}