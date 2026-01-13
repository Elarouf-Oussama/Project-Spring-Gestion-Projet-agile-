package com.agile_project_management.service;

import com.agile_project_management.model.Sprint;
import java.util.List;

public interface ISprintService {
    Sprint createSprint(Sprint sprint);
    Sprint updateSprint(Long id, Sprint sprint);
    Sprint getSprintById(Long id);
    List<Sprint> getAllSprints();
    void deleteSprint(Long id);
}