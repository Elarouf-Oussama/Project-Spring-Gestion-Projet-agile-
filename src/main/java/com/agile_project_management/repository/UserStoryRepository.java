package com.agile_project_management.repository;

import com.agile_project_management.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
    // Récupérer les stories d'un sprint spécifique
    List<UserStory> findBySprintBacklogId(Long sprintBacklogId);

    // Récupérer les stories d'un ProductBacklog
    List<UserStory> findByProductBacklogId(Long backlogId);
}