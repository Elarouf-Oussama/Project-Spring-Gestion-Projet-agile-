package com.agile_project_management.repository;

import com.agile_project_management.model.ProductBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductBacklogRepository extends JpaRepository<ProductBacklog, Long> {
}