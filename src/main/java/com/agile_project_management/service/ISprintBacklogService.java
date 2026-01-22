package com.agile_project_management.service;

import com.agile_project_management.model.SprintBacklog;

public interface ISprintBacklogService {
    // Créer le conteneur vide lors de la création d'un sprint
    SprintBacklog createSprintBacklogForSprint(Long sprintId);

    // Récupérer le backlog associé à un sprint
    SprintBacklog getSprintBacklogBySprintId(Long sprintId);
}