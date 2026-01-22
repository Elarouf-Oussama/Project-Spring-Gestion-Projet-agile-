package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.Sprint;
import com.agile_project_management.model.SprintBacklog;
import com.agile_project_management.repository.SprintBacklogRepository;
import com.agile_project_management.repository.SprintRepository;
import com.agile_project_management.service.ISprintBacklogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SprintBacklogServiceImpl implements ISprintBacklogService {

    private final SprintBacklogRepository sprintBacklogRepository;
    private final SprintRepository sprintRepository;

    @Override
    @Transactional
    public SprintBacklog createSprintBacklogForSprint(Long sprintId) {
        Sprint sprint = sprintRepository.findById(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found with id: " + sprintId));

        // Vérifier si un Sprint Backlog existe déjà pour éviter les doublons
        if (sprintBacklogRepository.findBySprintId(sprintId).isPresent()) {
            throw new RuntimeException("SprintBacklog already exists for this sprint");
        }

        SprintBacklog backlog = new SprintBacklog();
        backlog.setName("Backlog for Sprint " + sprint.getName());
        backlog.setSprint(sprint);

        return sprintBacklogRepository.save(backlog);
    }

    @Override
    public SprintBacklog getSprintBacklogBySprintId(Long sprintId) {
        return sprintBacklogRepository.findBySprintId(sprintId)
                .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog not found for sprint id: " + sprintId));
    }
}