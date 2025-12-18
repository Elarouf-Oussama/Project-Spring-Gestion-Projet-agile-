package com.agile_project_management.controller;

import com.agile_project_management.model.Sprint;
import com.agile_project_management.service.ISprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprints")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SprintController {

    private final ISprintService sprintService;

    @PostMapping
    public ResponseEntity<Sprint> createSprint(@RequestBody Sprint sprint) {
        return ResponseEntity.ok(sprintService.createSprint(sprint));
    }

    @GetMapping
    public ResponseEntity<List<Sprint>> getAllSprints() {
        return ResponseEntity.ok(sprintService.getAllSprints());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sprint> getSprintById(@PathVariable Long id) {
        return ResponseEntity.ok(sprintService.getSprintById(id));
    }
}