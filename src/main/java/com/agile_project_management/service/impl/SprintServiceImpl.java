package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.Sprint;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.repository.SprintRepository;
import com.agile_project_management.repository.UserStoryRepository;
import com.agile_project_management.service.ISprintBacklogService;
import com.agile_project_management.service.ISprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements ISprintService {

    private final SprintRepository sprintRepository;
    private final ISprintBacklogService sprintBacklogService;
    private final UserStoryRepository userStoryRepository;
    @Override
    @Transactional // garantir que Sprint + Backlog sont créés ensemble
    public Sprint createSprint(Sprint sprint) {
        sprint.setStatus("PLANNED");
        Sprint savedSprint = sprintRepository.save(sprint);

        // Création auto du SprintBacklog
        sprintBacklogService.createSprintBacklogForSprint(savedSprint.getId());
        return savedSprint;
    }

    @Override
    public Sprint updateSprint(Long id, Sprint sprintDetails) {
        Sprint sprint = getSprintById(id);
        sprint.setName(sprintDetails.getName());
        sprint.setStartDate(sprintDetails.getStartDate());
        sprint.setEndDate(sprintDetails.getEndDate());
        sprint.setStatus(sprintDetails.getStatus());
        return sprintRepository.save(sprint);
    }

    @Override
    public Sprint getSprintById(Long id) {
        return sprintRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sprint not found with id: " + id));
    }

    @Override
    public List<Sprint> getAllSprints() {
        return sprintRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteSprint(Long id) {
        Sprint sprint = getSprintById(id);

        if (sprint.getSprintBacklog() != null) {
            List<UserStory> stories = sprint.getSprintBacklog().getUserStories();
            // couper le lien avec le SprintBacklog pour chaque story
            for (UserStory story : stories) {
                story.setSprintBacklog(null);
                userStoryRepository.save(story);
            }
        }
        sprintRepository.delete(sprint);
    }
}