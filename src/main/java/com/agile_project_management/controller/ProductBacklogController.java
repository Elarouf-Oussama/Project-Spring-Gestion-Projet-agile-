package com.agile_project_management.controller;

import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.service.IProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-backlogs")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductBacklogController {

    private final IProductBacklogService productBacklogService;

    @PostMapping
    public ResponseEntity<ProductBacklog> createBacklog(@RequestBody ProductBacklog backlog) {
        return ResponseEntity.ok(productBacklogService.createProductBacklog(backlog));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductBacklog> getBacklogById(@PathVariable Long id) {
        return ResponseEntity.ok(productBacklogService.getProductBacklogById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductBacklog> updateBacklog(@PathVariable Long id, @RequestBody ProductBacklog backlog) {
        return ResponseEntity.ok(productBacklogService.updateProductBacklog(id, backlog));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBacklog(@PathVariable Long id) {
        productBacklogService.deleteProductBacklog(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ProductBacklog>> getAllBacklogs() {
        return ResponseEntity.ok(productBacklogService.getAllProductBacklogs());
    }
}