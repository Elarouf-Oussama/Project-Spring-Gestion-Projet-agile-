package com.agile_project_management.controller;

import com.agile_project_management.model.Epic;
import com.agile_project_management.service.IEpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/epics")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EpicController {

    private final IEpicService epicService;

    @PostMapping
    public ResponseEntity<Epic> createEpic(@RequestBody Epic epic) {
        return ResponseEntity.ok(epicService.createEpic(epic));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Epic> getEpicById(@PathVariable Long id) {
        return ResponseEntity.ok(epicService.getEpicById(id));
    }

    @GetMapping
    public ResponseEntity<List<Epic>> getAllEpics() {
        return ResponseEntity.ok(epicService.getAllEpics());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Epic> updateEpic(@PathVariable Long id, @RequestBody Epic epic) {
        return ResponseEntity.ok(epicService.updateEpic(id, epic));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEpic(@PathVariable Long id) {
        epicService.deleteEpic(id);
        return ResponseEntity.noContent().build();
    }
}