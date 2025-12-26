package com.agile_project_management.dto.response;

import com.agile_project_management.model.enums.Priority;
import com.agile_project_management.model.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserStoryResponse {
    private Long id;
    private String title;
    private String description;
    private String acceptanceCriteria;
    private Priority priority;
    private Integer storyPoints;
    private TaskStatus status;
    private String sprintName;
    private String epicTitle;
}