package com.agile_project_management.service;

import com.agile_project_management.model.Epic;
import java.util.List;

public interface IEpicService {
    Epic createEpic(Epic epic);
    Epic getEpicById(Long id);
    List<Epic> getAllEpics();
    Epic updateEpic(Long id, Epic epicDetails);
    void deleteEpic(Long id);
}