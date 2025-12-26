package com.agile_project_management.repository;

import com.agile_project_management.model.UserStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserStoryRepository extends JpaRepository<UserStory, Long> {
    // Récupérer les stories d'un sprint spécifique
    List<UserStory> findBySprintId(Long sprintId);

    // Récupérer les stories du backlog (non assignées à un sprint ou global)
    List<UserStory> findByProductBacklogId(Long backlogId);
}