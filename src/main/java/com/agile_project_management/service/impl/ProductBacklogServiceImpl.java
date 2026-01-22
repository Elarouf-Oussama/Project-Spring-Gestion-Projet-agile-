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

    private final ProductBacklogRepository backlogRepository;

    @Override
    public ProductBacklog createProductBacklog(ProductBacklog backlog) {
        return backlogRepository.save(backlog);
    }

    @Override
    public ProductBacklog getProductBacklogById(Long id) {
        return backlogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Backlog not found with id: " + id));
    }

    @Override
    public List<ProductBacklog> getAllProductBacklogs() {
        return backlogRepository.findAll();
    }

    @Override
    public ProductBacklog updateProductBacklog(Long id, ProductBacklog backlogDetails) {
        ProductBacklog existingBacklog = getProductBacklogById(id);
        if (backlogDetails.getNom() != null) {
            existingBacklog.setNom(backlogDetails.getNom());
        }
        return backlogRepository.save(existingBacklog);
    }

    @Override
    public void deleteProductBacklog(Long id) {
        ProductBacklog backlog = getProductBacklogById(id);
        backlogRepository.delete(backlog);
    }
}