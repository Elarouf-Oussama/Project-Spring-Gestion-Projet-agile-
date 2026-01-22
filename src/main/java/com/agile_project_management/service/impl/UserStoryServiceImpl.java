package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.model.SprintBacklog;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.model.enums.TaskStatus;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.repository.SprintBacklogRepository;
import com.agile_project_management.repository.UserStoryRepository;
import com.agile_project_management.service.IUserStoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserStoryServiceImpl implements IUserStoryService {

    private final UserStoryRepository userStoryRepository;
    private final ProductBacklogRepository productBacklogRepository;
    private final SprintBacklogRepository sprintBacklogRepository;

    @Override
    public UserStory createUserStory(UserStory userStory) {
        // Lien avec ProductBacklog
        if (userStory.getProductBacklog() != null && userStory.getProductBacklog().getId() != null) {
            ProductBacklog backlog = productBacklogRepository.findById(userStory.getProductBacklog().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("ProductBacklog not found"));
            userStory.setProductBacklog(backlog);
        }

        // Lien avec SprintBacklog
        if (userStory.getSprintBacklog() != null && userStory.getSprintBacklog().getId() != null) {
            SprintBacklog sb = sprintBacklogRepository.findById(userStory.getSprintBacklog().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog not found"));
            userStory.setSprintBacklog(sb);
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

        // Gestion du déplacement vers un SprintBacklog
        if (userStoryDetails.getSprintBacklog() != null) {
            SprintBacklog sb = sprintBacklogRepository.findById(userStoryDetails.getSprintBacklog().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("SprintBacklog not found"));
            story.setSprintBacklog(sb);
        } else {
            story.setSprintBacklog(null);
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
    public List<UserStory> getStoriesByProductBacklog(Long backlogId) {
        return userStoryRepository.findByProductBacklogId(backlogId);
    }

    @Override
    public List<UserStory> getStoriesBySprintBacklog(Long sprintBacklogId) {
        return userStoryRepository.findBySprintBacklogId(sprintBacklogId);
    }
}