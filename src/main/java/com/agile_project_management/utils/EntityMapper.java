package com.agile_project_management.utils;

import com.agile_project_management.dto.request.UserStoryRequest;
import com.agile_project_management.dto.response.UserStoryResponse;
import com.agile_project_management.model.Epic;
import com.agile_project_management.model.ProductBacklog;
import com.agile_project_management.model.SprintBacklog;
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

                // Mapping du ProductBacklog
                .productBacklog(request.getProductBacklogId() != null
                        ? ProductBacklog.builder().id(request.getProductBacklogId()).build() : null)

                // Mapping de l'Epic
                .epic(request.getEpicId() != null
                        ? Epic.builder().id(request.getEpicId()).build() : null)

                // Mapping du SprintBacklog
                .sprintBacklog(request.getSprintBacklogId() != null
                        ? SprintBacklog.builder().id(request.getSprintBacklogId()).build() : null)
                .build();
    }

    // Conversion Entity -> Response
    public UserStoryResponse toUserStoryResponse(UserStory story) {
        String sprintName = "Sprint_X";

        if (story.getSprintBacklog() != null && story.getSprintBacklog().getSprint() != null) {
            sprintName = story.getSprintBacklog().getSprint().getName();
        }

        return UserStoryResponse.builder()
                .id(story.getId())
                .title(story.getTitle())
                .description(story.getDescription())
                .acceptanceCriteria(story.getAcceptanceCriteria())
                .priority(story.getPriority())
                .storyPoints(story.getStoryPoints())
                .status(story.getStatus())
                .sprintName(sprintName)
                .epicTitle(story.getEpic() != null ? story.getEpic().getTitle() : null)
                .build();
    }
}