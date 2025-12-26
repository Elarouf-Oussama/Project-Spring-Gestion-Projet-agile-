package com.agile_project_management.controller;

import com.agile_project_management.dto.request.UserStoryRequest;
import com.agile_project_management.dto.response.UserStoryResponse;
import com.agile_project_management.model.UserStory;
import com.agile_project_management.model.enums.Priority;
import com.agile_project_management.service.IUserStoryService;
import com.agile_project_management.utils.EntityMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserStoryController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserStoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IUserStoryService userStoryService;

    @MockitoBean
    private EntityMapper mapper;

    @Autowired
    private ObjectMapper objectMapper; // Convertit les objets Java en JSON

    @Test
    void shouldCreateUserStoryAPI() throws Exception {
        // 1. Préparer la requête (Objet Java -> JSON)
        UserStoryRequest request = new UserStoryRequest();
        request.setTitle("Nouvelle fonctionnalité");
        request.setPriority(Priority.MUST_HAVE);
        request.setProductBacklogId(1L);

        // 2. Préparer les réponses simulées des Services (Mocks)
        UserStory mockEntity = UserStory.builder()
                .id(1L)
                .title("Nouvelle fonctionnalité")
                .priority(Priority.MUST_HAVE)
                .build();

        UserStoryResponse mockResponse = UserStoryResponse.builder()
                .id(1L)
                .title("Nouvelle fonctionnalité")
                .priority(Priority.MUST_HAVE)
                .build();

        Mockito.when(mapper.toUserStoryEntity(any(UserStoryRequest.class))).thenReturn(mockEntity);
        Mockito.when(userStoryService.createUserStory(any(UserStory.class))).thenReturn(mockEntity);
        Mockito.when(mapper.toUserStoryResponse(any(UserStory.class))).thenReturn(mockResponse);

        // 3. Exécuter la requête POST virtuelle et vérifier le résultat
        mockMvc.perform(post("/api/stories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Nouvelle fonctionnalité"))
                .andExpect(jsonPath("$.id").value(1));
    }
}