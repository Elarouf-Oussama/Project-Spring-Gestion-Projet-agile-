package com.agile_project_management.service;

import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.repository.ProductBacklogRepository;
import com.agile_project_management.service.impl.ProductBacklogServiceImpl;
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
class ProductBacklogServiceImplTest {

    @Mock
    private ProductBacklogRepository backlogRepository;

    @InjectMocks
    private ProductBacklogServiceImpl backlogService;

    @Test
    void shouldCreateBacklog() {
        ProductBacklog backlog = ProductBacklog.builder().nom("Projet Agile").build();
        when(backlogRepository.save(any(ProductBacklog.class))).thenReturn(backlog);

        ProductBacklog created = backlogService.createProductBacklog(backlog);

        assertEquals("Projet Agile", created.getNom());
    }

    @Test
    void shouldThrowException_WhenBacklogNotFound() {
        Long invalidId = 99L;
        when(backlogRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            backlogService.getProductBacklogById(invalidId);
        });
    }
}