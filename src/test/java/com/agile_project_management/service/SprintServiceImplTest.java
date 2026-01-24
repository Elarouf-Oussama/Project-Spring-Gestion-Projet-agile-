package com.agile_project_management.service;

import com.agile_project_management.model.Sprint;
import com.agile_project_management.repository.SprintRepository;
import com.agile_project_management.service.impl.SprintServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SprintServiceImplTest {

    @Mock
    private SprintRepository sprintRepository;

    @Mock
    private ISprintBacklogService sprintBacklogService;

    @InjectMocks
    private SprintServiceImpl sprintService;

    @Test
    void shouldCreateSprint_AndTriggerBacklogCreation() {
        // Arrange
        Sprint sprintToCreate = Sprint.builder()
                .name("Sprint 1")
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusWeeks(2))
                .build();

        Sprint savedSprint = Sprint.builder()
                .id(1L)
                .name("Sprint 1")
                .status("PLANNED")
                .build();

        when(sprintRepository.save(any(Sprint.class))).thenReturn(savedSprint);

        // Act
        Sprint result = sprintService.createSprint(sprintToCreate);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("PLANNED", result.getStatus());

        // Vérification de la création du SprintBacklog associé
        verify(sprintBacklogService, times(1)).createSprintBacklogForSprint(1L);
    }

    @Test
    void shouldGetSprintById() {
        // Arrange
        Long sprintId = 1L;
        Sprint mockSprint = Sprint.builder().id(sprintId).name("Sprint Test").build();
        when(sprintRepository.findById(sprintId)).thenReturn(Optional.of(mockSprint));

        // Act
        Sprint foundSprint = sprintService.getSprintById(sprintId);

        // Assert
        assertEquals("Sprint Test", foundSprint.getName());
    }
}