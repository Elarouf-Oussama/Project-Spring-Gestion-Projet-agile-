package com.agile_project_management.controller;

import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.service.IProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/backlogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BacklogController {

    private final IProductBacklogService backlogService;

    // Créer un nouveau Backlog pour un projet
    @PostMapping
    public ResponseEntity<ProductBacklog> createBacklog(@RequestBody ProductBacklog backlog) {
        return ResponseEntity.ok(backlogService.createBacklog(backlog));
    }

    // Récupérer un Backlog
    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklog> getBacklogById(@PathVariable Long id) {
        return ResponseEntity.ok(backlogService.getBacklogById(id));
    }
}