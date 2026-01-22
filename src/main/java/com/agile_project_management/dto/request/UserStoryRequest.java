package com.agile_project_management.dto.request;

import com.agile_project_management.model.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserStoryRequest {
    @NotBlank(message = "Le titre est obligatoire")
    private String title;
    private String description;
    private String acceptanceCriteria;
    private Priority priority;
    private Integer storyPoints;
    private Long productBacklogId;
    private Long epicId;
    private Long sprintBacklogId;
}