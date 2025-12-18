package com.agile_project_management.service;

import com.agile_project_management.model.UserStory;
import java.util.List;

public interface IUserStoryService {
    UserStory createUserStory(UserStory userStory);
    UserStory updateUserStory(Long id, UserStory userStory);
    void deleteUserStory(Long id);
    UserStory getUserStoryById(Long id);

    // Logique métier spécifique agile
    List<UserStory> getStoriesByBacklog(Long backlogId);
    List<UserStory> getStoriesBySprint(Long sprintId);
}