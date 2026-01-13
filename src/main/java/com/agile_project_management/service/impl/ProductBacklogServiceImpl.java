package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.service.IProductBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductBacklogServiceImpl implements IProductBacklogService {

    private final ProductBacklogRepository backlogRepository;

    @Override
    public ProductBacklog createBacklog(ProductBacklog backlog) {
        return backlogRepository.save(backlog);
    }

    @Override
    public ProductBacklog getBacklogById(Long id) {
        return backlogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Backlog not found with id: " + id));
    }
}