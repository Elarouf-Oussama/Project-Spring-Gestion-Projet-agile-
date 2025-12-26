package com.agile_project_management.controller;

import com.agile_project_management.dto.request.UpdateTaskStatusRequest;
import com.agile_project_management.model.Task;
import com.agile_project_management.service.ITaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {

    private final ITaskService taskService;

    // Ajouter une tâche à une User Story
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestParam Long storyId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(storyId, task));
    }

    // Changer le statut d'une tâche
    @PatchMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestBody UpdateTaskStatusRequest request) {
        return ResponseEntity.ok(taskService.updateTaskStatus(id, request.getStatus()));
    }

    // Assigner un développeur à une tâche
    @PatchMapping("/{id}/assign/{userId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long id, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.assignTaskToUser(id, userId));
    }

    // Récupérer les tâches d'une story
    @GetMapping("/story/{storyId}")
    public ResponseEntity<List<Task>> getTasksByStory(@PathVariable Long storyId) {
        return ResponseEntity.ok(taskService.getTasksByUserStory(storyId));
    }
}