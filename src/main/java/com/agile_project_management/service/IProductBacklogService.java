package com.agile_project_management.service;

import com.agile_project_management.model.ProductBacklog;
import java.util.List;

public interface IProductBacklogService {
    ProductBacklog createProductBacklog(ProductBacklog backlog);
    ProductBacklog getProductBacklogById(Long id);
    List<ProductBacklog> getAllProductBacklogs();
    ProductBacklog updateProductBacklog(Long id, ProductBacklog backlogDetails);
    void deleteProductBacklog(Long id);
}