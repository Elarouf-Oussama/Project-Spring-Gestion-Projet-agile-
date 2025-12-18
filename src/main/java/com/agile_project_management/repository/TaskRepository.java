package com.agile_project_management.repository;

import com.agile_project_management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUserStoryId(Long userStoryId);
    List<Task> findByAssigneeId(Long userId);
}