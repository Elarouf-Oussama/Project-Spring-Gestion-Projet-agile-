package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.Epic;
import com.agile_project_management.repository.EpicRepository;
import com.agile_project_management.service.IEpicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements IEpicService {

    private final EpicRepository epicRepository;

    @Override
    public Epic createEpic(Epic epic) {
        return epicRepository.save(epic);
    }

    @Override
    public Epic getEpicById(Long id) {
        return epicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Epic not found with id: " + id));
    }

    @Override
    public List<Epic> getAllEpics() {
        return epicRepository.findAll();
    }

    @Override
    public Epic updateEpic(Long id, Epic epicDetails) {
        Epic epic = getEpicById(id);
        epic.setTitle(epicDetails.getTitle());
        epic.setDescription(epicDetails.getDescription());
        return epicRepository.save(epic);
    }

    @Override
    public void deleteEpic(Long id) {
        Epic epic = getEpicById(id);
        epicRepository.delete(epic);
    }
}