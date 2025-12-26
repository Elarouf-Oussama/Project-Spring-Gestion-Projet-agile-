package com.agile_project_management.service;

import com.agile_project_management.model.ProductBacklog;
import java.util.List;

public interface IProductBacklogService {
    ProductBacklog createBacklog(ProductBacklog backlog);
    ProductBacklog getBacklogById(Long id);

    List<ProductBacklog> getAllBacklogs();
    ProductBacklog updateBacklog(Long id, ProductBacklog backlogDetails);
}