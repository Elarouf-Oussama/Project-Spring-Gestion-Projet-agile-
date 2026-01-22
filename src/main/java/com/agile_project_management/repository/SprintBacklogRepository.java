package com.agile_project_management.repository;

import com.agile_project_management.model.SprintBacklog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SprintBacklogRepository extends JpaRepository<SprintBacklog, Long> {
    // retrouver le backlog associé à un sprint spécifique
    Optional<SprintBacklog> findBySprintId(Long sprintId);
}