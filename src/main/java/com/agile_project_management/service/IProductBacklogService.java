package com.agile_project_management.service;

import com.agile_project_management.model.ProductBacklog;

public interface IProductBacklogService {
    ProductBacklog createBacklog(ProductBacklog backlog);
    ProductBacklog getBacklogById(Long id);
    // Ajout d'autres méthodes plus tard
}