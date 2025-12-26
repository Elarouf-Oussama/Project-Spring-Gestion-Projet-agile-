package com.agile_project_management.controller;

import com.agile_project_management.dto.request.UserStoryRequest;
import com.agile_project_management.dto.response.UserStoryResponse;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.service.IUserStoryService;
import com.agile_project_management.utils.EntityMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stories")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Autorise le frontend (Angular/React)
public class UserStoryController {

    private final IUserStoryService userStoryService;
    private final EntityMapper mapper;

    // 1. Créer une User Story
    @PostMapping
    public ResponseEntity<UserStoryResponse> createUserStory(@Valid @RequestBody UserStoryRequest request) {
        UserStory entity = mapper.toUserStoryEntity(request);
        UserStory createdStory = userStoryService.createUserStory(entity);
        return new ResponseEntity<>(mapper.toUserStoryResponse(createdStory), HttpStatus.CREATED);
    }

    // 2. Récupérer toutes les stories d'un Sprint
    @GetMapping("/sprint/{sprintId}")
    public ResponseEntity<List<UserStoryResponse>> getStoriesBySprint(@PathVariable Long sprintId) {
        List<UserStory> stories = userStoryService.getStoriesBySprint(sprintId);
        List<UserStoryResponse> response = stories.stream()
                .map(mapper::toUserStoryResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 3. Récupérer toutes les stories du Backlog
    @GetMapping("/backlog/{backlogId}")
    public ResponseEntity<List<UserStoryResponse>> getStoriesByBacklog(@PathVariable Long backlogId) {
        List<UserStory> stories = userStoryService.getStoriesByBacklog(backlogId);
        List<UserStoryResponse> response = stories.stream()
                .map(mapper::toUserStoryResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // 4. Mettre à jour une Story
    @PutMapping("/{id}")
    public ResponseEntity<UserStoryResponse> updateUserStory(@PathVariable Long id, @RequestBody UserStoryRequest request) {
        UserStory entityDetails = mapper.toUserStoryEntity(request);
        UserStory updatedStory = userStoryService.updateUserStory(id, entityDetails);
        return ResponseEntity.ok(mapper.toUserStoryResponse(updatedStory));
    }

    // 5. Supprimer une Story
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserStory(@PathVariable Long id) {
        userStoryService.deleteUserStory(id);
        return ResponseEntity.noContent().build();
    }
}