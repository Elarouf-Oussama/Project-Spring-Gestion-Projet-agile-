package com.agile_project_management.service;

import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.model.SprintBacklog;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.repository.SprintBacklogRepository;
import com.agile_project_management.repository.UserStoryRepository;
import com.agile_project_management.service.impl.UserStoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserStoryServiceImplTest {

    @Mock
    private UserStoryRepository userStoryRepository;
    @Mock
    private ProductBacklogRepository backlogRepository;
    @Mock
    private SprintBacklogRepository sprintBacklogRepository;

    @InjectMocks
    private UserStoryServiceImpl userStoryService;

    @Test
    void shouldCreateUserStory_LinkedToProductBacklog() {
        // Arrange
        ProductBacklog backlog = ProductBacklog.builder().id(10L).build();
        UserStory story = UserStory.builder().title("Login").productBacklog(backlog).build();

        when(backlogRepository.findById(10L)).thenReturn(Optional.of(backlog));
        when(userStoryRepository.save(any(UserStory.class))).thenReturn(story);

        // Act
        UserStory created = userStoryService.createUserStory(story);

        // Assert
        assertNotNull(created);
        verify(backlogRepository, times(1)).findById(10L);
        verify(userStoryRepository, times(1)).save(story);
    }

    @Test
    void shouldUpdateUserStory_AndMoveToSprint() {
        // Arrange
        Long storyId = 1L;
        Long sprintBacklogId = 50L;

        UserStory existingStory = UserStory.builder().id(storyId).title("Old Title").build();

        // Détails envoyés pour la mise à jour
        SprintBacklog targetSprintBacklog = SprintBacklog.builder().id(sprintBacklogId).build();
        UserStory details = UserStory.builder().title("New Title").sprintBacklog(targetSprintBacklog).build();

        // Mocks
        when(userStoryRepository.findById(storyId)).thenReturn(Optional.of(existingStory));
        when(sprintBacklogRepository.findById(sprintBacklogId)).thenReturn(Optional.of(targetSprintBacklog));
        when(userStoryRepository.save(any(UserStory.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        UserStory updated = userStoryService.updateUserStory(storyId, details);

        // Assert
        assertEquals("New Title", updated.getTitle());
        assertNotNull(updated.getSprintBacklog());
        assertEquals(50L, updated.getSprintBacklog().getId());
    }
}