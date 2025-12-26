package com.agile_project_management.controller;

import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.service.IProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/backlogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class BacklogController {

    private final IProductBacklogService backlogService;

    @PostMapping
    public ResponseEntity<ProductBacklog> createBacklog(@RequestBody ProductBacklog backlog) {
        return ResponseEntity.ok(backlogService.createBacklog(backlog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklog> getBacklogById(@PathVariable Long id) {
        return ResponseEntity.ok(backlogService.getBacklogById(id));
    }

    // GET /api/backlogs (Pour la page d'accueil)
    @GetMapping
    public ResponseEntity<List<ProductBacklog>> getAllBacklogs() {
        return ResponseEntity.ok(backlogService.getAllBacklogs());
    }

    // PUT /api/backlogs/{id} (Pour renommer un projet)
    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklog> updateBacklog(@PathVariable Long id, @RequestBody ProductBacklog backlog) {
        return ResponseEntity.ok(backlogService.updateBacklog(id, backlog));
    }
}