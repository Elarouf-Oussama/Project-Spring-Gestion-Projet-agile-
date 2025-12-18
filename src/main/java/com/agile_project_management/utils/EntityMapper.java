package com.agile_project_management.utils;

import com.agile_project_management.dto.request.UserStoryRequest;
import com.agile_project_management.dto.response.UserStoryResponse;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.model.Sprint;
import com.agile_project_management.model.UserStory;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    // Conversion Request -> Entity
    public UserStory toUserStoryEntity(UserStoryRequest request) {
        return UserStory.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .acceptanceCriteria(request.getAcceptanceCriteria())
                .priority(request.getPriority())
                .storyPoints(request.getStoryPoints())
                // Les relations (Backlog, Sprint) sont gérées dans le Service via les ID
                .productBacklog(ProductBacklog.builder().id(request.getProductBacklogId()).build())
                .sprint(request.getSprintId() != null ? Sprint.builder().id(request.getSprintId()).build() : null)
                .build();
    }

    // Conversion Entity -> Response
    public UserStoryResponse toUserStoryResponse(UserStory story) {
        return UserStoryResponse.builder()
                .id(story.getId())
                .title(story.getTitle())
                .description(story.getDescription())
                .acceptanceCriteria(story.getAcceptanceCriteria())
                .priority(story.getPriority())
                .storyPoints(story.getStoryPoints())
                .status(story.getStatus())
                .sprintName(story.getSprint() != null ? story.getSprint().getName() : "Backlog")
                .epicTitle(story.getEpic() != null ? story.getEpic().getTitle() : null)
                .build();
    }
}