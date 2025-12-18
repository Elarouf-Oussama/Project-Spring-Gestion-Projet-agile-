package com.agile_project_management.service.impl;

import com.agile_project_management.exception.ResourceNotFoundException;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.model.enums.Priority;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.repository.UserStoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserStoryServiceImplTest {

    @Mock
    private UserStoryRepository userStoryRepository;

    @Mock
    private ProductBacklogRepository backlogRepository;

    @InjectMocks
    private UserStoryServiceImpl userStoryService;

    @Test
    void shouldCreateUserStorySuccessfully() {
        // 1. Préparer les données (Given)
        ProductBacklog backlog = ProductBacklog.builder().id(1L).nom("Projet Alpha").build();

        UserStory inputStory = UserStory.builder()
                .title("Connexion")
                .productBacklog(backlog)
                .priority(Priority.HIGH)
                .build();

        UserStory savedStory = UserStory.builder()
                .id(100L) // Simule l'ID généré par la BDD
                .title("Connexion")
                .productBacklog(backlog)
                .status(com.agile_project_management.model.enums.TaskStatus.TO_DO)
                .build();

        // 2. Définir le comportement des Mocks (When)
        Mockito.when(backlogRepository.findById(1L)).thenReturn(Optional.of(backlog));
        Mockito.when(userStoryRepository.save(any(UserStory.class))).thenReturn(savedStory);

        // 3. Exécuter la méthode à tester (Act)
        UserStory result = userStoryService.createUserStory(inputStory);

        // 4. Vérifications (Assert)
        Assertions.assertNotNull(result);
        Assertions.assertEquals(100L, result.getId());
        Assertions.assertEquals("TO_DO", result.getStatus().name());

        Mockito.verify(userStoryRepository, Mockito.times(1)).save(any(UserStory.class));
    }

    @Test
    void shouldThrowExceptionWhenBacklogNotFound() {
        // 1. Given
        ProductBacklog backlog = ProductBacklog.builder().id(99L).build();
        UserStory inputStory = UserStory.builder().productBacklog(backlog).build();

        // 2. When (Backlog introuvable)
        Mockito.when(backlogRepository.findById(99L)).thenReturn(Optional.empty());

        // 3. Act & Assert
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            userStoryService.createUserStory(inputStory);
        });

        Mockito.verify(userStoryRepository, Mockito.never()).save(any());
    }
}