package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.model.enums.TaskStatus;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.repository.UserStoryRepository;
import com.agile_project_management.service.IUserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements IUserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final ProductBacklogRepository backlogRepository;

    @Override
    public UserStory createUserStory(UserStory userStory) {
        if (userStory.getProductBacklog() != null && userStory.getProductBacklog().getId() != null) {
            ProductBacklog backlog = backlogRepository.findById(userStory.getProductBacklog().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Backlog not found"));
            userStory.setProductBacklog(backlog);
        }

        if (userStory.getStatus() == null) {
            userStory.setStatus(TaskStatus.TO_DO);
        }
        return userStoryRepository.save(userStory);
    }

    @Override
    public UserStory updateUserStory(Long id, UserStory userStoryDetails) {
        UserStory story = getUserStoryById(id);

        story.setTitle(userStoryDetails.getTitle());
        story.setDescription(userStoryDetails.getDescription());
        story.setPriority(userStoryDetails.getPriority());
        story.setStoryPoints(userStoryDetails.getStoryPoints());
        story.setAcceptanceCriteria(userStoryDetails.getAcceptanceCriteria());

        if (userStoryDetails.getSprint() != null) {
            story.setSprint(userStoryDetails.getSprint());
        }

        return userStoryRepository.save(story);
    }

    @Override
    public void deleteUserStory(Long id) {
        UserStory story = getUserStoryById(id);
        userStoryRepository.delete(story);
    }

    @Override
    public UserStory getUserStoryById(Long id) {
        return userStoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("UserStory not found with id: " + id));
    }

    @Override
    public List<UserStory> getStoriesByBacklog(Long backlogId) {
        return userStoryRepository.findByProductBacklogId(backlogId);
    }

    @Override
    public List<UserStory> getStoriesBySprint(Long sprintId) {
        return userStoryRepository.findBySprintId(sprintId);
    }
}