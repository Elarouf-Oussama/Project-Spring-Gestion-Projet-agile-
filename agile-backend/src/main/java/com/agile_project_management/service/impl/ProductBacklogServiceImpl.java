package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.service.IProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductBacklogServiceImpl implements IProductBacklogService {

    private final ProductBacklogRepository repository;

    @Override
    public ProductBacklog createBacklog(ProductBacklog backlog) {
        return repository.save(backlog);
    }

    @Override
    public ProductBacklog getBacklogById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Backlog not found with id: " + id));
    }

    @Override
    public List<ProductBacklog> getAllBacklogs() {
        return repository.findAll();
    }

    @Override
    public ProductBacklog updateBacklog(Long id, ProductBacklog backlogDetails) {
        ProductBacklog existingBacklog = getBacklogById(id);

        existingBacklog.setNom(backlogDetails.getNom());
        existingBacklog.setDescription(backlogDetails.getDescription());

        return repository.save(existingBacklog);
    }
}