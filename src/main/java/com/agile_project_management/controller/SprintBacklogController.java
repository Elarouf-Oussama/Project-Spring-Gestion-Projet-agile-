package com.agile_project_management.controller;

import com.agile_project_management.model.SprintBacklog;
import com.agile_project_management.service.ISprintBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sprint-backlogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SprintBacklogController {

    private final ISprintBacklogService sprintBacklogService;

    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<SprintBacklog> getBacklogBySprintId(@PathVariable Long sprintId) {
        return ResponseEntity.ok(sprintBacklogService.getSprintBacklogBySprintId(sprintId));
    }
}