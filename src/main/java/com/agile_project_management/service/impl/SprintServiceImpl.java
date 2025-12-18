package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.Sprint;
import com.agile_project_management.repository.SprintRepository;
import com.agile_project_management.service.ISprintService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintServiceImpl implements ISprintService {

    private final SprintRepository sprintRepository;

    @Override
    public Sprint createSprint(Sprint sprint) {
        sprint.setStatus("PLANNED"); // Statut par défaut
        return sprintRepository.save(sprint);
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
    public void deleteSprint(Long id) {
        Sprint sprint = getSprintById(id);
        sprintRepository.delete(sprint);
    }
}
